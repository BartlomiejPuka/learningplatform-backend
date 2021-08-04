package pl.edu.wszib.learningplatform.enrolledcourse.taskprogress;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskProgressRepository extends JpaRepository<TaskProgress, Long> {

    List<TaskProgress> findByUserCourseIdAndUserCourseUserId(Long courseId, Long userId);

    @Query("SELECT tp FROM TaskProgress tp " +
            "WHERE tp.userCourse.course.details.urlSlug = :courseUrlSlug " +
            "and tp.userCourse.user.id = :userId ")
    List<TaskProgress> findByCourseUrlSlugAndUserId(String courseUrlSlug, Long userId);

    TaskProgress findByUserCourseIdAndTaskOrderIdAndUserCourseUserId(Long courseId, Long taskOrderId, Long userId);

}
