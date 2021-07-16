package pl.edu.wszib.learningplatform.usercourse.lessonprogress;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonProgressRepository extends JpaRepository<LessonProgress, Long> {
}
