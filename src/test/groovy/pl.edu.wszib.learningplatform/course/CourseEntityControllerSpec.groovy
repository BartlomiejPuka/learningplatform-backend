package pl.edu.wszib.learningplatform.course

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.context.support.WithUserDetails
import org.springframework.test.context.ActiveProfiles
import pl.edu.wszib.learningplatform.BaseIT
import pl.edu.wszib.learningplatform.user.User
import pl.edu.wszib.learningplatform.user.UserRepository

import static org.springframework.http.HttpStatus.BAD_REQUEST
import static org.springframework.http.HttpStatus.OK


@WithUserDetails("testUser")
@ActiveProfiles("test")
class CourseEntityControllerSpec extends BaseIT {

    @Autowired
    UserRepository userRepository

    def "should be able to get list of courses" () {
        when:
            List<User> users = userRepository.findAll();
            def response = performGet("/api/courses", null)
        then:
            verifyAll {
                response.status == OK.value()
                response.contentType == 'application/json'
            }
    }
}
