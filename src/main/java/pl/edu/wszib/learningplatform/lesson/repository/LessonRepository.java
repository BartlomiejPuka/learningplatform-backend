package pl.edu.wszib.learningplatform.lesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.learningplatform.lesson.model.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
