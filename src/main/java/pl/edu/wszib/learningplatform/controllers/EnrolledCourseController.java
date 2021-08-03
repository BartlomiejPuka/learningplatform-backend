package pl.edu.wszib.learningplatform.controllers;

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

    @GetMapping("/{courseId}/lessons/{lessonOrderId}/details")
    @ResponseStatus(HttpStatus.OK)
    public EnrolledLessonDetailsDto getCourseLessonDetails(@PathVariable("courseId") Long courseId,
                                                           @PathVariable("lessonOrderId") Long lessonOrderId,
                                                           @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return enrolledLessonService.getCourseLessonDetails(courseId, lessonOrderId, userPrincipal.getUser());
    }

    @GetMapping("/{courseId}/tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<EnrolledTaskDto> getAllCourseTasks(@PathVariable("courseId") Long courseId,
                                                   @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return enrolledTaskService.getAllCourseTasks(courseId, userPrincipal.getUser());
    }

    @GetMapping("/{courseId}/tasks/{taskOrderId}/details")
    @ResponseStatus(HttpStatus.OK)
    public EnrolledTaskDetailsDto getCourseTaskDetails(@PathVariable("courseId") Long courseId,
                                                       @PathVariable("taskOrderId") Long taskOrderId,
                                                       @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return enrolledTaskService.getCourseTaskDetails(courseId, taskOrderId, userPrincipal.getUser());
    }

    @PutMapping("/{courseId}/tasks/{taskOrderId}/complete")
    @ResponseStatus(HttpStatus.OK)
    public void completeCourseTask(@PathVariable("courseId") Long courseId,
                                   @PathVariable("taskOrderId") Long taskOrderId,
                                   @AuthenticationPrincipal UserPrincipal userPrincipal) {
        enrolledTaskService.completeCourseTask(courseId, taskOrderId, userPrincipal.getUser());
    }

    @PutMapping("/{courseId}/lessons/{lessonOrderId}/complete")
    @ResponseStatus(HttpStatus.OK)
    public void completeCourseLesson(@PathVariable("courseId") Long courseId,
                                   @PathVariable("lessonOrderId") Long lessonOrderId,
                                   @AuthenticationPrincipal UserPrincipal userPrincipal) {
        enrolledLessonService.completeCourseLesson(courseId, lessonOrderId, userPrincipal.getUser());
    }
}
