package pl.edu.wszib.learningplatform.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.learningplatform.course.*;
import pl.edu.wszib.learningplatform.course.lesson.LessonDto;
import pl.edu.wszib.learningplatform.course.task.TaskDto;
import pl.edu.wszib.learningplatform.enrolledcourse.enrolledlesson.EnrolledLessonDetailsDto;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/courses")
@Slf4j
@Tag(name = "Course - General", description = "All endpoint that gives general data about courses.")
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get list of courses with general information about them.", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = CourseDto.class)))})
    })
    public List<CourseDto> getCourses(CourseCriteria courseCriteria){
        return courseService.getCourses(courseCriteria);
    }

    @GetMapping("/categorized")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get map of courses categorized by course category.", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = CourseDto.class)))})
    })
    public Map<String, List<CourseDto>> getCategorizedCourses() {
        return courseService.getCategorizedCourses();
    }

    @GetMapping("/categories")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all course categories.", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = CourseCategoryDto.class)))})
    })
    public List<CourseCategoryDto> getCategories() {
        return courseService.getCategories();
    }

    @GetMapping("/categories/{slug}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get course category.", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = CourseCategoryDto.class))})
    })
    public CourseCategoryDto getCategoryByUrlSlug(@PathVariable("slug") String urlSlug) {
        return courseService.getCategoryByUrlSlug(urlSlug);
    }


//   @GetMapping("/{id}/details")
//   @ResponseStatus(HttpStatus.OK)
//    public CourseDetailsDto getCourseDetails(@PathVariable("id") Long courseId) {
//        return courseService.getCourseDetails(courseId);
//    }

//    @GetMapping("/{slug}/details")
//    @ResponseStatus(HttpStatus.OK)
//    public CourseDetailsDto getCourseDetails(@PathVariable("slug") String urlSlug) {
//        return courseService.getCourseDetailsByUrlSlug(urlSlug);
//    }

    /*@GetMapping("/{id}/lessons")
    @ResponseStatus(HttpStatus.OK)
    public List<LessonDto> getCourseLessons(@PathVariable("id") Long courseId){
        return courseService.getCourseLessons(courseId);
    }

    @GetMapping("/{id}/tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDto> getCourseTasks(@PathVariable("id") Long courseId){
        return courseService.getCourseTasks(courseId);
    }*/



//    @GetMapping("/categories/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public CourseCategoryDto getCategoryById(@PathVariable("id") Long courseCategoryId) {
//        return courseService.getCategoryById(courseCategoryId);
//    }



}
