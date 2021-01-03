package pl.edu.wszib.learningplatform.components;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.edu.wszib.learningplatform.controllers.assemblers.LessonAssembler;
import pl.edu.wszib.learningplatform.controllers.dto.LessonDto;
import pl.edu.wszib.learningplatform.lesson.model.Lesson;
import pl.edu.wszib.learningplatform.lesson.service.LessonService;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class LessonComponent {
    private final LessonService lessonService;
    private final LessonAssembler lessonAssembler;

    public List<LessonDto> getLessonsBySubCourseId(Long subCourseId){
        List<Lesson> lessonsModels = lessonService.getLessonsBySubCourseId(subCourseId);
        return lessonsModels.stream().map(lessonAssembler::toDto).collect(toList());
    }
}
