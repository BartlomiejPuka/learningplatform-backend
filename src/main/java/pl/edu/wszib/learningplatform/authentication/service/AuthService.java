package pl.edu.wszib.learningplatform.authentication.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.edu.wszib.learningplatform.authentication.dto.AuthenticationResponse;
import pl.edu.wszib.learningplatform.authentication.dto.LoginRequest;
import pl.edu.wszib.learningplatform.refreshtoken.RefreshTokenRequest;
import pl.edu.wszib.learningplatform.authentication.dto.RegisterRequest;
import pl.edu.wszib.learningplatform.authentication.email.MailContentBuilder;
import pl.edu.wszib.learningplatform.authentication.email.MailService;
import pl.edu.wszib.learningplatform.authentication.email.NotificationEmail;
import pl.edu.wszib.learningplatform.refreshtoken.RefreshTokenService;
import pl.edu.wszib.learningplatform.enrolledcourse.EnrolledCourseCreationService;
import pl.edu.wszib.learningplatform.util.exceptions.UserAlreadyExistsException;
import pl.edu.wszib.learningplatform.authentication.model.VerificationToken;
import pl.edu.wszib.learningplatform.authentication.repository.VerificationTokenRepository;
import pl.edu.wszib.learningplatform.security.JwtProvider;
import pl.edu.wszib.learningplatform.user.User;
import pl.edu.wszib.learningplatform.user.UserRepository;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

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
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    private final RefreshTokenService refreshTokenService;

    private final EnrolledCourseCreationService userCourseCreationService;

    /**
     * (1) sprawdza czy użytkownik z podanym username istnieje w bazie danych juz w bazie danych
     * (2) Zapisuje dane użytkownika wraz z zhaszowanym hasłem
     * (3) Generuje token, który użytkownik potrzebuje by aktywować swoje konto (enabled=1)
     * (4) Wysyła mail do użytkownika.
     * @param registerRequest
     * @return
     */
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


    /**
     * Tworzy unikatowy token, który jest przypiswywany danemu użytkownikowi i zapisywany jest do bazy.
     * @param user
     * @return
     */
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

    /**
     * (1) Tworzymy UserNamePasswordAuthenticationToken na podstawie danych wysłanych z formularza do logowania
     * (2) UseDetailsServiceImpl sprawdza czy użytkownik o podanym loginie istnieje
     * (3) Jeśli istnieje to jest nastepnie sprawdzene czy jest enabled=1, w przeciwnym razie otrzyjmujemy AccessDenied
     * (4) Generujemy JWT
     * (5) Zwracamy nazwe uzytkownika, JWT, refresh token i czas wygasniecia tokena
     * @param loginRequest
     * @return
     */
    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String authenticationToken = jwtProvider.generateToken(authenticate);
        return AuthenticationResponse.builder()
                .authenticationToken(authenticationToken)
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(loginRequest.getUsername())
                .build();
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        String token = jwtProvider.generateTokenWithUserName(refreshTokenRequest.getUsername());
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(refreshTokenRequest.getUsername())
                .build();
    }
}
