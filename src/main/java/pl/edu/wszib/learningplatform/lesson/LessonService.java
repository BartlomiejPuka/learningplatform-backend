package pl.edu.wszib.learningplatform.lesson;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;

    public List<LessonEntity> getLessonsBySubCourseId(Long subCourseId) { return lessonRepository.findAllBySubCourseId(subCourseId); }
}
