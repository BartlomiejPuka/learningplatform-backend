package pl.edu.wszib.learningplatform.course


import pl.edu.wszib.learningplatform.BaseIT

import static org.springframework.http.HttpStatus.OK

class CourseEntityControllerSpec extends BaseIT {

    def "should be able to get list of courses"() {
        when:
            def response = performGet("/api/courses", null)
        then:
            verifyAll {
                response.status == OK.value()
                response.contentType == 'application/json'
            }
    }
}
