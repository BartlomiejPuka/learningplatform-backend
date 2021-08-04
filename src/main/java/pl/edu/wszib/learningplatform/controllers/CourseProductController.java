package pl.edu.wszib.learningplatform.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
import pl.edu.wszib.learningplatform.user.UserPrincipal;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/products")
@Slf4j
@Tag(name = "Course - Products", description = "Rest endpoints for course products")
public class CourseProductController {

    private final CourseProductService courseProductService;
    private final CourseProductDetailsService courseProductDetailsService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get list of all course products.", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = {
                    @Content(
                            array = @ArraySchema(schema = @Schema(implementation = CourseProductDto.class))
                    )})
    })
    public List<CourseProductDto> getAllCourseProducts(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return courseProductService.getAllCourseProducts(userPrincipal.getUser());
    }

    @GetMapping("/category/{slug}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get list of all course products by category.", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = {
                    @Content(
                            array = @ArraySchema(schema = @Schema(implementation = CourseProductDto.class))
                    )})
    })
    public List<CourseProductDto> getCourseProductsByUrlSlug(@PathVariable("slug") String urlSlug,
                                                          @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return courseProductService.getCourseProductsByUrlSlug(urlSlug, userPrincipal.getUser());
    }

    @GetMapping("/course/{slug}/details")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = String.class))})
    })
    public CourseProductDetailsDto getCourseProductDetails(@PathVariable("slug") String urlSlug,
                                                    @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return courseProductDetailsService.getCourseProductDetailsByUrlSlug(urlSlug, userPrincipal.getUser());
    }
}
