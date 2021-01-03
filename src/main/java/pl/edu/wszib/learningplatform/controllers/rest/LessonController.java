package pl.edu.wszib.learningplatform.controllers.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wszib.learningplatform.components.LessonComponent;
import pl.edu.wszib.learningplatform.controllers.dto.LessonDto;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class LessonController {

    private final LessonComponent lessonComponent;

    @GetMapping(value = "/subcourses/{subCourseId}/lessons")
    public ResponseEntity<List<LessonDto>> getLessons(@PathVariable Long subCourseId){
        return ResponseEntity.status(HttpStatus.OK).body(lessonComponent.getLessonsBySubCourseId(subCourseId));
    }
}
