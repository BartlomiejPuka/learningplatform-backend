package pl.edu.wszib.learningplatform.authentication.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import pl.edu.wszib.learningplatform.authentication.service.UserDetailsServiceImpl;
import pl.edu.wszib.learningplatform.user.UserPrincipal;

import java.time.Instant;
import java.util.Date;

import static java.util.stream.Collectors.toList;

@Component
public class JwtProvider {

    @Value("${jwt.expiration.time}")
    private long expirationTime;
    @Value("${jwt.secret}")
    private String secret;

    private final UserDetailsServiceImpl userDetailsService;

    public JwtProvider(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public String generateToken(String username){
        UserPrincipal userPrincipal = (UserPrincipal) userDetailsService.loadUserByUsername(username);
        return JWT.create()
                .withSubject(userPrincipal.getUsername())
                .withExpiresAt(Date.from(Instant.now().plusMillis(expirationTime)))
                .withIssuer("/api/auth/refreshToken")
                .withClaim("roles", userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(toList()))
                .sign(Algorithm.HMAC256(secret));
    }


}
