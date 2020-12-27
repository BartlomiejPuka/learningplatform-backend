package pl.edu.wszib.learningplatform.lesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wszib.learningplatform.lesson.model.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
