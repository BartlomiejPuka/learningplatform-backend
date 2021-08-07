package pl.edu.wszib.learningplatform.enrolledcourse.lessonprogress;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LessonProgressRepository extends JpaRepository<LessonProgress, Long> {

    List<LessonProgress> findByUserCourseIdAndUserCourseUserId(Long courseId, Long userId);

    LessonProgress findByUserCourseIdAndLessonOrderIdAndUserCourseUserId(Long courseId, Long lessonOrderId, Long userId);

    @Query("SELECT lp FROM LessonProgress lp " +
            "WHERE lp.userCourse.course.details.urlSlug = :courseUrlSlug " +
            "AND lp.userCourse.user.id = :userId " +
            "ORDER BY lp.lesson.orderId ASC")
    List<LessonProgress> findByCourseUrlSlugAndUserId(String courseUrlSlug, Long userId);
}
