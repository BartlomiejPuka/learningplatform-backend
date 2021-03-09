package pl.edu.wszib.learningplatform.course;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "Get list of courses.",
            description = "Get list of available courses.",
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = {@Content(
                                    array = @ArraySchema(schema = @Schema(implementation = UserCourseDto.class))
                            )})
            })
    public List<CourseDto> getCourses(){
        return courseService.getCourses();
    }

    @GetMapping("user")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get list of courses.",
            description = "Get list of available courses with additional enrollment information of logged in user.",
            responses = {
            @ApiResponse(responseCode = "200",
                    content = {@Content(
                           array = @ArraySchema(schema = @Schema(implementation = UserCourseDto.class))
                    )})
            })
    public List<UserCourseDto> getCoursesByUserId(){ return courseService.getUserCourses(); }


    @PostMapping("{id}/enroll")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Enroll course for logged user.",
            description = "Enroll selected course for currently logged user.",
            responses = {@ApiResponse(responseCode = "200")})
    public void enrollCourse(@PathVariable("id") Long courseId){
        courseService.enrollCourse(courseId);
    }


}
