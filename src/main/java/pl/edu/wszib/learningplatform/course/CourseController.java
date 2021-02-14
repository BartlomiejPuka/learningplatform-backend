package pl.edu.wszib.learningplatform.course;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.learningplatform.user.UserComponent;
import pl.edu.wszib.learningplatform.enrollment.EnrollmentDto;
import pl.edu.wszib.learningplatform.user.UserDto;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@Slf4j
public class CourseController {
    private final UserComponent userComponent;
    private final CourseComponent courseComponent;

    @ApiOperation(value = "Get all users that enrolled particular course")
    @GetMapping(value = "/courses/{courseId}/enrolled-users", produces = "application/json")
    public ResponseEntity<List<UserDto>> getUsersByCourse(@PathVariable long courseId){
        return ResponseEntity.status(HttpStatus.OK).body(userComponent.getAllUsersByCourse(courseId));
    }

    @ApiOperation(value = "Get all courses")
    @GetMapping(value = "/courses", produces = "application/json")
    public ResponseEntity<List<CourseDto>> getCourses(){
        return ResponseEntity.status(HttpStatus.OK).body(courseComponent.getCourses());
    }

    @ApiOperation(value = "Enroll user to particular course")
    @PostMapping(value = "/courses", produces = "application/json")
    public ResponseEntity<EnrollmentDto> startCourse(@RequestBody StartCourseDto startCourseDto){
        return ResponseEntity.status(HttpStatus.OK).body(courseComponent.startCourse(startCourseDto));
    }
}