package pl.edu.wszib.learningplatform.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.learningplatform.courseproducts.CourseProductDetailsDto;
import pl.edu.wszib.learningplatform.courseproducts.CourseProductDetailsService;
import pl.edu.wszib.learningplatform.courseproducts.CourseProductDto;
import pl.edu.wszib.learningplatform.courseproducts.CourseProductService;
import pl.edu.wszib.learningplatform.enrolledcourse.EnrolledCourseService;
import pl.edu.wszib.learningplatform.user.UserPrincipal;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/products")
@Slf4j
@Tag(name = "Course Product Tag", description = "Rest endpoints for course products")
public class CourseProductController {

    private final CourseProductService courseProductService;
    private final CourseProductDetailsService courseProductDetailsService;
    private final EnrolledCourseService enrolledCourseService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CourseProductDto> getAllCourseProducts(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return courseProductService.getAllCourseProducts(userPrincipal.getUser());
    }

    @GetMapping("/category/{slug}")
    @ResponseStatus(HttpStatus.OK)
    public List<CourseProductDto> getCourseProductsByUrlSlug(@PathVariable("slug") String urlSlug,
                                                          @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return courseProductService.getCourseProductsByUrlSlug(urlSlug, userPrincipal.getUser());
    }

    @GetMapping("/course/{slug}/details")
    @ResponseStatus(HttpStatus.OK)
    public CourseProductDetailsDto getCourseDetails(@PathVariable("slug") String urlSlug,
                                                    @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return courseProductDetailsService.getCourseDetailsByUrlSlug(urlSlug, userPrincipal.getUser());
    }
}
