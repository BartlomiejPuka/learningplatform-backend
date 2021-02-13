package pl.edu.wszib.learningplatform.lesson;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class LessonComponent {
    private final LessonService lessonService;
    private final LessonMapper lessonAssembler;

    public List<LessonDto> getLessonsBySubCourseId(Long subCourseId){
        List<LessonEntity> lessonsModels = lessonService.getLessonsBySubCourseId(subCourseId);
        return lessonsModels.stream().map(lessonAssembler::toDto).collect(toList());
    }
}
