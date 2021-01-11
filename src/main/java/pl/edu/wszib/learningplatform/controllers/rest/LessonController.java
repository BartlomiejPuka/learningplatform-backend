package pl.edu.wszib.learningplatform.controllers.rest;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class LessonController {

    private final LessonComponent lessonComponent;

    @ApiOperation(value = "Get all lessons for particular subcourse")
    @GetMapping(value = "/subcourses/{subCourseId}/lessons", produces = "application/json")
    public ResponseEntity<List<LessonDto>> getLessons(@PathVariable Long subCourseId){
        return ResponseEntity.status(HttpStatus.OK).body(lessonComponent.getLessonsBySubCourseId(subCourseId));
    }
}
