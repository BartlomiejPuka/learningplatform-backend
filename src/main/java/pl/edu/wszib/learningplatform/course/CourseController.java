package pl.edu.wszib.learningplatform.course;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/courses")
@Slf4j
@Tag(name = "Course Tag", description = "Rest endpoints for courses")
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CourseDto> getCourses(CourseCriteria courseCriteria){
        return courseService.getCourses(courseCriteria);
    }


    @GetMapping("/categorized")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, List<CourseDto>> getCategorizedCourses() {
        return courseService.getCategorizedCourses();
    }

}
