package pl.edu.wszib.learningplatform.enrolledcourse.lessonprogress;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonProgressRepository extends JpaRepository<LessonProgress, Long> {

    List<LessonProgress> findByUserCourseIdAndUserCourseUserId(Long courseId, Long userId);

    LessonProgress findByUserCourseIdAndLessonIdAndUserCourseUserId(Long courseId, Long taskId, Long userId);

}
