package pl.edu.wszib.learningplatform.authentication.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.edu.wszib.learningplatform.authentication.dto.RegisterRequest;
import pl.edu.wszib.learningplatform.authentication.email.MailContentBuilder;
import pl.edu.wszib.learningplatform.authentication.email.MailService;
import pl.edu.wszib.learningplatform.authentication.email.NotificationEmail;
import pl.edu.wszib.learningplatform.authentication.jwt.JwtProvider;
import pl.edu.wszib.learningplatform.authentication.refreshtoken.RefreshTokenRequest;
import pl.edu.wszib.learningplatform.authentication.refreshtoken.RefreshTokenService;
import pl.edu.wszib.learningplatform.authentication.verificationtoken.VerificationToken;
import pl.edu.wszib.learningplatform.authentication.verificationtoken.VerificationTokenRepository;
import pl.edu.wszib.learningplatform.enrolledcourse.EnrolledCourseCreationService;
import pl.edu.wszib.learningplatform.user.User;
import pl.edu.wszib.learningplatform.user.UserRepository;
import pl.edu.wszib.learningplatform.util.exceptions.UserAlreadyExistsException;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

import static pl.edu.wszib.learningplatform.authentication.jwt.SecurityConstant.AUTHORIZATION_HEADER;
import static pl.edu.wszib.learningplatform.authentication.jwt.SecurityConstant.BEARER_PREFIX;
import static pl.edu.wszib.learningplatform.util.Constants.ACTIVATION_EMAIL;
import static pl.edu.wszib.learningplatform.util.message.MessageTemplates.USER_ALREADY_EXISTS_MESSAGE_TEMPLATE;

@Service
@AllArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;

    private final EnrolledCourseCreationService userCourseCreationService;

    @Transactional
    public boolean signup(RegisterRequest registerRequest){

        if(userRepository.findByUsername(registerRequest.getUsername()).isPresent()){
            throw new UserAlreadyExistsException(String.format(USER_ALREADY_EXISTS_MESSAGE_TEMPLATE, registerRequest.getUsername()));
        }

        User user = registerRequest.toUser();
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        userRepository.save(user);

        userCourseCreationService.setupUserCourses(user);

        String token = generateVerificationToken(user);

        String message = mailContentBuilder.build(
                "Thank you for signing up to our learning platform, please click on the below url to activate your account : "
                + ACTIVATION_EMAIL + "/" + token);
        mailService.sendMail(new NotificationEmail("Please Activate your account", user.getEmail(), message));
        return true;
    }


    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationTokenRepository.save(verificationToken);
        return token;
    }

    public void verifyAccount(String token){
        Optional<VerificationToken> verificationTokenOptional = verificationTokenRepository.findByToken(token);
        verificationTokenOptional.orElseThrow(() -> new RuntimeException("Invalid token"));
        fetchUserAndEnable(verificationTokenOptional.get());
    }

    @Transactional
    public void fetchUserAndEnable(VerificationToken verificationToken) {
        User user = verificationToken.getUser();
        user.setEnabled(true);
        userRepository.save(user);
    }

    public ResponseEntity<?> refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String refreshToken = refreshTokenRequest.getRefreshToken();
        refreshTokenService.validateRefreshToken(refreshToken);
        refreshTokenService.deleteRefreshToken(refreshToken);
        String token = jwtProvider.generateToken(refreshTokenRequest.getUsername());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(AUTHORIZATION_HEADER, BEARER_PREFIX + token);
        responseHeaders.set("refresh-token", refreshTokenService.generateRefreshToken().getToken());
        return ResponseEntity.ok().headers(responseHeaders).build();
    }
}
