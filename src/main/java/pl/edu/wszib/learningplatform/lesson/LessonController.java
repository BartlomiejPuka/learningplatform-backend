package pl.edu.wszib.learningplatform.lesson;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@Slf4j
public class LessonController {

    private final LessonComponent lessonComponent;

//    @ApiOperation(value = "Get all lessons for particular subcourse")
//    @GetMapping(value = "/subcourses/{subCourseId}/lessons", produces = "application/json")
//    public ResponseEntity<List<LessonDto>> getLessons(@PathVariable Long courseId){
//        return ResponseEntity.status(HttpStatus.OK).body(lessonComponent.getLessonsBySubCourseId(subCourseId));
//    }
}
