package pl.edu.wszib.learningplatform.authentication.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import pl.edu.wszib.learningplatform.authentication.refreshtoken.RefreshTokenService;
import pl.edu.wszib.learningplatform.user.UserPrincipal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;

import static java.util.stream.Collectors.toList;
import static pl.edu.wszib.learningplatform.authentication.jwt.SecurityConstant.AUTHORIZATION_HEADER;
import static pl.edu.wszib.learningplatform.authentication.jwt.SecurityConstant.BEARER_PREFIX;

@Component
public class RestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final long expirationTime;
    private final String secret;
    private final RefreshTokenService refreshTokenService;

    public RestAuthenticationSuccessHandler(
            @Value("${jwt.expiration.time}") long expirationTime,
            @Value("${jwt.secret}") String secret,
            RefreshTokenService refreshTokenService) {
        this.expirationTime = expirationTime;
        this.secret = secret;
        this.refreshTokenService = refreshTokenService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String token = JWT.create()
                .withSubject(userPrincipal.getUsername())
                .withExpiresAt(Date.from(Instant.now().plusMillis(expirationTime)))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(toList()))
                .sign(Algorithm.HMAC256(secret));
        response.addHeader(AUTHORIZATION_HEADER, BEARER_PREFIX + token);
        response.addHeader("refresh-token", refreshTokenService.generateRefreshToken().getToken());
    }
}
