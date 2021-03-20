package pl.edu.wszib.learningplatform


import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.jdbc.Sql

@SpringBootApplication(exclude = [SecurityAutoConfiguration.class])
@ActiveProfiles("test")
@Sql(scripts = "classpath:test-users.sql")
class TestLearningPlatformApplication {

}
