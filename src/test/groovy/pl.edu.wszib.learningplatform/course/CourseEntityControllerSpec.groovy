package pl.edu.wszib.learningplatform.course

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.context.support.WithUserDetails
import pl.edu.wszib.learningplatform.BaseIT
import pl.edu.wszib.learningplatform.user.User
import pl.edu.wszib.learningplatform.user.UserRepository

import static org.springframework.http.HttpStatus.BAD_REQUEST
import static org.springframework.http.HttpStatus.OK

@WithUserDetails("testUser")
class CourseEntityControllerSpec extends BaseIT {

    @Autowired
    UserRepository userRepository

    def "should be able to get list of courses" () {
        when:
            List<User> users = userRepository.findAll();
            def response = performGet("/api/course", null)
        then:
            verifyAll {
                response.status == OK.value()
                response.contentType == 'application/json'
            }
    }

    def "should not be able to enroll course that does not exists" () {
        given:
            List<User> users = userRepository.findAll();
            Long id = -99
        when:
            def response = performPost("/api/course/${id}/enroll", null)
        then:
            verifyAll {
                response.status == BAD_REQUEST.value()
                response.contentType == 'application/json'
            }
    }
}
