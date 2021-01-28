package pl.edu.wszib.learningplatform.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
@RequiredArgsConstructor
public class PropertiesConstants {

    @Value("${springdoc.swagger-ui.url-pattern}")
    private String[] swaggerUrlPattern;
}
