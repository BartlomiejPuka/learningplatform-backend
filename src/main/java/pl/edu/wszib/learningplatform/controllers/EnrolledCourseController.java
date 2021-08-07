package pl.edu.wszib.learningplatform.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.learningplatform.course.CourseDto;
import pl.edu.wszib.learningplatform.enrolledcourse.EnrolledCourseDto;
import pl.edu.wszib.learningplatform.enrolledcourse.EnrolledCourseService;
import pl.edu.wszib.learningplatform.enrolledcourse.enrolledlesson.EnrolledLessonDetailsDto;
import pl.edu.wszib.learningplatform.enrolledcourse.enrolledlesson.EnrolledLessonDto;
import pl.edu.wszib.learningplatform.enrolledcourse.enrolledlesson.EnrolledLessonService;
import pl.edu.wszib.learningplatform.enrolledcourse.enrolledtask.EnrolledTaskDetailsDto;
import pl.edu.wszib.learningplatform.enrolledcourse.enrolledtask.EnrolledTaskDto;
import pl.edu.wszib.learningplatform.enrolledcourse.enrolledtask.EnrolledTaskService;
import pl.edu.wszib.learningplatform.user.UserPrincipal;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/courses")
@Tag(name = "Course - Enrolled", description = "Endpoints that manages information about user courses.")
public class EnrolledCourseController {

    private final EnrolledCourseService enrolledCourseService;
    private final EnrolledLessonService enrolledLessonService;
    private final EnrolledTaskService enrolledTaskService;

    @GetMapping("/bought")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get list of courses that particular user owns.", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = {
                    @Content(
                            array = @ArraySchema(
                                    schema = @Schema(implementation = EnrolledCourseDto.class)
                            )
                    )})
    })
    public List<EnrolledCourseDto> getAllBoughtCourses(@AuthenticationPrincipal UserPrincipal userPrincipal){
        return enrolledCourseService.getAllBoughtCourses(userPrincipal.getUser());
    }

    @GetMapping("/not-bought")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get list of courses that particular user does not own.", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = {
                    @Content(
                            array = @ArraySchema(
                                    schema = @Schema(implementation = CourseDto.class)
                            )
                    )})
    })
    public List<CourseDto> getAllNotBoughtCourses(@AuthenticationPrincipal UserPrincipal userPrincipal){
        return enrolledCourseService.getAllNotBoughtCourses(userPrincipal.getUser());
    }

    @GetMapping("/{courseUrlSlug}")
    @ResponseStatus(HttpStatus.OK)
    public EnrolledCourseDto getCourseByCourseUrlSlug(@PathVariable("courseUrlSlug") String courseUrlSlug,
                                                      @AuthenticationPrincipal UserPrincipal userPrincipal){
        return enrolledCourseService.getCourseByCourseUrlSlug(courseUrlSlug, userPrincipal.getUser());
    }

    @GetMapping("/{courseUrlSlug}/lessons")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get list of lessons by particular course.", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = {
                    @Content(
                            array = @ArraySchema(
                                    schema = @Schema(implementation = EnrolledLessonDto.class)
                            )
                    )})
    })
    public List<EnrolledLessonDto> getAllCourseLessons(@PathVariable("courseUrlSlug") String courseUrlSlug,
                                                       @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return enrolledLessonService.getAllCourseLessons(courseUrlSlug, userPrincipal.getUser());
    }

    @GetMapping("/{courseUrlSlug}/lessons/{lessonUrlSlug}/details")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get details of lesson by particular course.", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = {
                    @Content(
                            schema = @Schema(implementation = EnrolledLessonDetailsDto.class)
                    )})
    })
    public EnrolledLessonDetailsDto getCourseLessonDetails(@PathVariable("courseUrlSlug") String courseUrlSlug,
                                                           @PathVariable("lessonUrlSlug") String lessonUrlSlug,
                                                           @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return enrolledLessonService.getCourseLessonDetails(courseUrlSlug, lessonUrlSlug, userPrincipal.getUser());
    }

    @GetMapping("/{courseUrlSlug}/tasks")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get list of tasks by particular course.", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = {
                    @Content(
                            array = @ArraySchema(
                                    schema = @Schema(implementation = EnrolledTaskDto.class)
                            )
                    )})
    })
    public List<EnrolledTaskDto> getAllCourseTasks(@PathVariable("courseUrlSlug") String courseUrlSlug,
                                                   @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return enrolledTaskService.getAllCourseTasks(courseUrlSlug, userPrincipal.getUser());
    }

    @GetMapping("/{courseUrlSlug}/tasks/{taskUrlSlug}/details")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get details of task by particular course.", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = {
                    @Content(
                            schema = @Schema(
                                    implementation = EnrolledTaskDetailsDto.class
                            )
                    )})
    })
    public EnrolledTaskDetailsDto getCourseTaskDetails(@PathVariable("courseUrlSlug") String courseUrlSlug,
                                                       @PathVariable("taskUrlSlug") String taskUrlSlug,
                                                       @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return enrolledTaskService.getCourseTaskDetails(courseUrlSlug, taskUrlSlug, userPrincipal.getUser());
    }

    @PutMapping("/{courseUrlSlug}/tasks/{taskUrlSlug}/complete")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Complete course task by user.", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200")
    })
    public void completeCourseTask(@PathVariable("courseUrlSlug") String courseUrlSlug,
                                   @PathVariable("taskUrlSlug") String taskUrlSlug,
                                   @AuthenticationPrincipal UserPrincipal userPrincipal) {
        enrolledTaskService.completeCourseTask(courseUrlSlug, taskUrlSlug, userPrincipal.getUser());
    }

    @PutMapping("/{courseUrlSlug}/lessons/{lessonUrlSlug}/complete")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Complete course lesson by user.", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200")
    })
    public void completeCourseLesson(@PathVariable("courseUrlSlug") String courseUrlSlug,
                                   @PathVariable("lessonUrlSlug") String lessonUrlSlug,
                                   @AuthenticationPrincipal UserPrincipal userPrincipal) {
        enrolledLessonService.completeCourseLesson(courseUrlSlug, lessonUrlSlug, userPrincipal.getUser());
    }
}
