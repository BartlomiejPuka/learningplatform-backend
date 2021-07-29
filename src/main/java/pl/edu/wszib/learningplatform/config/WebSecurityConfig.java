package pl.edu.wszib.learningplatform.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import pl.edu.wszib.learningplatform.authentication.jwt.*;
import pl.edu.wszib.learningplatform.authentication.refreshtoken.RefreshTokenService;
import pl.edu.wszib.learningplatform.authentication.service.UserDetailsServiceImpl;
import pl.edu.wszib.learningplatform.util.PropertiesConstants;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final PropertiesConstants propertiesConstants;
    private final PasswordEncoder passwordEncoder;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final ObjectMapper objectMapper;
    private final RestAuthenticationFailureHandler restAuthenticationFailureHandler;
    private final RestAuthenticationSuccessHandler restAuthenticationSuccessHandler;
    @Value("${jwt.secret}")
    private String secret;

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();

        httpSecurity.authorizeRequests()
                .antMatchers("/api/auth/**/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(authenticationFilter())
                .addFilter(new JwtAuthorizationFilter(authenticationManagerBean(), userDetailsServiceImpl, secret))
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint);
    }

    public JsonObjectAuthenticationFilter authenticationFilter() throws Exception {
        JsonObjectAuthenticationFilter authenticationFilter = new JsonObjectAuthenticationFilter(objectMapper);
        authenticationFilter.setAuthenticationSuccessHandler(restAuthenticationSuccessHandler);
        authenticationFilter.setAuthenticationFailureHandler(restAuthenticationFailureHandler);
        authenticationFilter.setAuthenticationManager(authenticationManagerBean());
        authenticationFilter.setFilterProcessesUrl("/api/auth/login");
        return authenticationFilter;
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder
                .userDetailsService(userDetailsServiceImpl)
                .passwordEncoder(passwordEncoder);
    }

}
