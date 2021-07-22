package pl.edu.wszib.learningplatform.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wszib.learningplatform.authentication.service.CustomUser;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/enrolled-courses")
public class EnrolledCourseController {

    private final EnrolledCourseService enrolledCourseService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EnrolledCourseDto> getAllEnrolledCourses(@AuthenticationPrincipal CustomUser customUser){
        return enrolledCourseService.getAllEnrolledCourses(customUser.getUser());
    }
}
