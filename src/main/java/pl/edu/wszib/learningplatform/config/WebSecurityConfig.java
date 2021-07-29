package pl.edu.wszib.learningplatform.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.edu.wszib.learningplatform.authentication.jwt.CustomAuthenticationFilter;
import pl.edu.wszib.learningplatform.authentication.jwt.JwtAuthenticationEntryPoint;
import pl.edu.wszib.learningplatform.authentication.jwt.JwtAuthenticationFilter;
import pl.edu.wszib.learningplatform.authentication.refreshtoken.RefreshTokenService;
import pl.edu.wszib.learningplatform.authentication.service.UserDetailsServiceImpl;
import pl.edu.wszib.learningplatform.util.PropertiesConstants;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final PropertiesConstants propertiesConstants;
    private final RefreshTokenService refreshTokenService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.authorizeRequests().anyRequest().permitAll();
        httpSecurity.addFilter(new CustomAuthenticationFilter(authenticationManagerBean(), refreshTokenService));
//        httpSecurity.authorizeRequests()
//                    .antMatchers("/api/auth/**").permitAll()
//                    .antMatchers(propertiesConstants.getSwaggerUrlPattern()).permitAll()
//                    .anyRequest().authenticated()
//                .and()
//                    .exceptionHandling()
//                    .authenticationEntryPoint(jwtAuthenticationEntryPoint);
//        httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
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
