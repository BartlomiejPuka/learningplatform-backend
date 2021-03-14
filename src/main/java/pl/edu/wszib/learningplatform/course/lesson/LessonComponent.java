package pl.edu.wszib.learningplatform.course.lesson;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LessonComponent {
    private final LessonService lessonService;
    private final LessonMapper lessonAssembler;

//    public List<LessonDto> getLessonsBySubCourseId(Long courseId){
//    }
}
