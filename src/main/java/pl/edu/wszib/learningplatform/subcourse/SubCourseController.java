package pl.edu.wszib.learningplatform.subcourse;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@Slf4j
public class SubCourseController {

    private final SubCourseComponent subCourseComponent;

    @ApiOperation(value = "Get all subcourses of given course")
    @GetMapping(value = "/courses/{courseId}/subcourses", produces = "application/json")
    public ResponseEntity<List<SubCourseDto>> getSubCourses(@PathVariable Long courseId){
        return ResponseEntity.status(HttpStatus.OK).body(subCourseComponent.GetSubCourses(courseId));
    }

}
