package pl.edu.wszib.learningplatform.course;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/courses")
@Slf4j
@Tag(name = "Course Tag", description = "Rest endpoints for courses")
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CourseDto> getCourses(){
        return courseService.getCourses();
    }

    @GetMapping("user")
    @ResponseStatus(HttpStatus.OK)
    public List<UserCourseDto> getCoursesByUserId(){ return courseService.getUserCourses(); }


    @PostMapping("{id}/enroll")
    @ResponseStatus(HttpStatus.OK)
    public void enrollCourse(@PathVariable("id") Long courseId){
        courseService.enrollCourse(courseId);
    }


}
