package pl.edu.wszib.learningplatform.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.learningplatform.course.CourseCategoryDto;
import pl.edu.wszib.learningplatform.course.CourseCriteria;
import pl.edu.wszib.learningplatform.course.CourseDto;
import pl.edu.wszib.learningplatform.course.CourseService;
import pl.edu.wszib.learningplatform.course.lesson.LessonDto;
import pl.edu.wszib.learningplatform.course.task.TaskDto;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/courses")
@Slf4j
@Tag(name = "Course Tag", description = "All endpoint that gives data about courses.")
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

 /*   TODO: course details endpoint
   @GetMapping("/{id}/details")
    public List<Object> getCourseDetails(@PathVariable("id") Long courseId) {
        return courseService.getCourseDetails(courseId);
    }
*/
    @GetMapping("/{id}/lessons")
    @ResponseStatus(HttpStatus.OK)
    public List<LessonDto> getCourseLessons(@PathVariable("id") Long courseId){
        return courseService.getCourseLessons(courseId);
    }

    @GetMapping("/{id}/tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDto> getCourseTasks(@PathVariable("id") Long courseId){
        return courseService.getCourseTasks(courseId);
    }

    @GetMapping("/categories")
    @ResponseStatus(HttpStatus.OK)
    public List<CourseCategoryDto> getCategories() {
        return courseService.getCategories();
    }

    @GetMapping("/categories/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CourseCategoryDto getCategoryById(@PathVariable("id") Long courseCategoryId) {
        return courseService.getCategoryById(courseCategoryId);
    }
}
