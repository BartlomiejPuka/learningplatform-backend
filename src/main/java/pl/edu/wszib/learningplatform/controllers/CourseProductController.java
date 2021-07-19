package pl.edu.wszib.learningplatform.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.learningplatform.authentication.service.CustomUser;
import pl.edu.wszib.learningplatform.courseproducts.CourseProductDto;
import pl.edu.wszib.learningplatform.courseproducts.CourseProductService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/products")
@Slf4j
@Tag(name = "Course Product Tag", description = "Rest endpoints for course products")
public class CourseProductController {

    private final CourseProductService courseProductService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CourseProductDto> getAllCourseProducts(@AuthenticationPrincipal CustomUser customUser) {
        return courseProductService.getAllCourseProducts(customUser.getUser());
    }

    @GetMapping("/category/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<CourseProductDto> getCourseProductsByCategory(@PathVariable("id") Long categoryId,
                                                              @AuthenticationPrincipal CustomUser customUser) {
        return courseProductService.getCourseProductsByCategory(categoryId, customUser.getUser());
    }
}
