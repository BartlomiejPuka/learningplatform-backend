package pl.edu.wszib.learningplatform.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wszib.learningplatform.course.CourseDto;
import pl.edu.wszib.learningplatform.enrolledcourse.EnrolledCourseDto;
import pl.edu.wszib.learningplatform.enrolledcourse.EnrolledCourseService;
import pl.edu.wszib.learningplatform.user.UserPrincipal;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/courses")
public class EnrolledCourseController {

    private final EnrolledCourseService enrolledCourseService;

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
}
