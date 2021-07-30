package pl.edu.wszib.learningplatform.authentication.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import pl.edu.wszib.learningplatform.authentication.service.UserDetailsServiceImpl;
import pl.edu.wszib.learningplatform.exception.ApiErrorResponse;
import pl.edu.wszib.learningplatform.user.UserPrincipal;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Collections.singletonList;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static pl.edu.wszib.learningplatform.authentication.jwt.SecurityConstant.AUTHORIZATION_HEADER;
import static pl.edu.wszib.learningplatform.authentication.jwt.SecurityConstant.BEARER_PREFIX;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private final UserDetailsServiceImpl userDetailsService;
    private final ObjectMapper objectMapper;
    private final PasswordEncoder passwordEncoder;
    private final String secret;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager,
                                  UserDetailsServiceImpl userDetailsService,
                                  ObjectMapper objectMapper, PasswordEncoder passwordEncoder, String secret) {
        super(authenticationManager);
        this.userDetailsService = userDetailsService;
        this.objectMapper = objectMapper;
        this.passwordEncoder = passwordEncoder;
        this.secret = secret;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {
        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(request, response);
        if (authenticationToken == null) {
            filterChain.doFilter(request, response);
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String token = request.getHeader(AUTHORIZATION_HEADER);
        String userName = null;
        if (token != null && token.startsWith(BEARER_PREFIX)) {
            try{
                userName = getSubjectFromJwt(token);
            } catch (TokenExpiredException exception) {
                log.info(exception.getMessage());
                setExpiredTokenMessage(exception, response);
                return null;
            }
            if (userName != null) {
                UserPrincipal userPrincipal = (UserPrincipal) userDetailsService.loadUserByUsername(userName);
                return new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
            }
        }
        return null;
    }

    private String getSubjectFromJwt(String token) {
        return JWT.require(Algorithm.HMAC256(secret))
                    .build()
                    .verify(token.replace(BEARER_PREFIX, ""))
                    .getSubject();
    }

    private void setExpiredTokenMessage(TokenExpiredException exception, HttpServletResponse response) {
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
                .message("JWT is expired.")
                .errors(singletonList(exception.getMessage()))
                .build();
        response.setStatus(UNAUTHORIZED.value());
        try {
            objectMapper.writeValue(response.getOutputStream(), apiErrorResponse);
        } catch (IOException e) {
            e.printStackTrace();
            log.info(e.getMessage());
        }
    }
}
