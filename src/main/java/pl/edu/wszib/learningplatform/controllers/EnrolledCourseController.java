package pl.edu.wszib.learningplatform.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.learningplatform.course.CourseDto;
import pl.edu.wszib.learningplatform.enrolledcourse.EnrolledCourseDto;
import pl.edu.wszib.learningplatform.enrolledcourse.EnrolledCourseService;
import pl.edu.wszib.learningplatform.enrolledcourse.enrolledlesson.EnrolledLessonDto;
import pl.edu.wszib.learningplatform.enrolledcourse.enrolledlesson.EnrolledLessonService;
import pl.edu.wszib.learningplatform.enrolledcourse.enrolledtask.EnrolledTaskDto;
import pl.edu.wszib.learningplatform.enrolledcourse.enrolledtask.EnrolledTaskService;
import pl.edu.wszib.learningplatform.user.UserPrincipal;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/courses")
public class EnrolledCourseController {

    private final EnrolledCourseService enrolledCourseService;
    private final EnrolledLessonService enrolledLessonService;
    private final EnrolledTaskService enrolledTaskService;

    @GetMapping("/bought")
    @ResponseStatus(HttpStatus.OK)
    public List<EnrolledCourseDto> getAllBoughtCourses(@AuthenticationPrincipal UserPrincipal userPrincipal){
        return enrolledCourseService.getAllBoughtCourses(userPrincipal.getUser());
    }

    @GetMapping("/not-bought")
    @ResponseStatus(HttpStatus.OK)
    public List<CourseDto> getAllNotBoughtCourses(@AuthenticationPrincipal UserPrincipal userPrincipal){
        return enrolledCourseService.getAllNotBoughtCourses(userPrincipal.getUser());
    }

    @GetMapping("/{courseId}/lessons")
    @ResponseStatus(HttpStatus.OK)
    public List<EnrolledLessonDto> getAllCourseLessons(@PathVariable("courseId") Long courseId,
                                                       @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return enrolledLessonService.getAllCourseLessons(courseId, userPrincipal.getUser());
    }

    /*@GetMapping("/{courseId}/lessons/{lessonId}/details")
    @ResponseStatus(HttpStatus.OK)
    public List<EnrolledLessonDetailsDto> getCourseLessonDetails(@PathVariable("courseId") Long courseId,
                                                                 @PathVariable("lessonId") Long lessonId,
                                                                 @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return enrolledLessonService.getCourseLessonDetails(courseId, lessonId, userPrincipal.getUser());
    }*/

    @GetMapping("/{courseId}/tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<EnrolledTaskDto> getAllCourseTasks(@PathVariable("courseId") Long courseId,
                                                   @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return enrolledTaskService.getAllCourseTasks(courseId, userPrincipal.getUser());
    }

    /*@GetMapping("/{courseId}/tasks/{taskId}/details")
    @ResponseStatus(HttpStatus.OK)
    public List<EnrolledLessonDetailsDto> getCourseTaskDetails(@PathVariable("courseId") Long courseId,
                                                                @PathVariable("taskId") Long taskId,
                                                                @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return enrolledTaskService.getCourseTaskDetails(courseId, taskId, userPrincipal.getUser());
    }*/

    @PutMapping("/{courseId}/tasks/{taskId}/complete")
    @ResponseStatus(HttpStatus.OK)
    public void completeCourseTask(@PathVariable("courseId") Long courseId,
                                   @PathVariable("taskId") Long taskId,
                                   @AuthenticationPrincipal UserPrincipal userPrincipal) {
        enrolledTaskService.completeCourseTask(courseId, taskId, userPrincipal.getUser());
    }

    @PutMapping("/{courseId}/lessons/{lessonId}/complete")
    @ResponseStatus(HttpStatus.OK)
    public void completeCourseLesson(@PathVariable("courseId") Long courseId,
                                   @PathVariable("lessonId") Long lessonId,
                                   @AuthenticationPrincipal UserPrincipal userPrincipal) {
        enrolledLessonService.completeCourseLesson(courseId, lessonId, userPrincipal.getUser());
    }
}
