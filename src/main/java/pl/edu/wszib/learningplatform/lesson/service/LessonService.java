package pl.edu.wszib.learningplatform.lesson.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wszib.learningplatform.lesson.model.Lesson;
import pl.edu.wszib.learningplatform.lesson.repository.LessonRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;

    public List<Lesson> getLessonsBySubCourseId(Long subCourseId) { return lessonRepository.findAllBySubCourseId(subCourseId); }
}
