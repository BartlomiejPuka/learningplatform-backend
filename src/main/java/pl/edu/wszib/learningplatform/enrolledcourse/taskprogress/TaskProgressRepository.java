package pl.edu.wszib.learningplatform.enrolledcourse.taskprogress;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskProgressRepository extends JpaRepository<TaskProgress, Long> {

    @Query("SELECT tp FROM TaskProgress tp " +
            "WHERE tp.userCourse.course.details.urlSlug = :courseUrlSlug " +
            "AND tp.userCourse.user.id = :userId ")
    List<TaskProgress> findByCourseUrlSlugAndUserId(String courseUrlSlug, Long userId);


    @Query("SELECT tp FROM TaskProgress tp " +
            "WHERE tp.userCourse.course.details.urlSlug = :courseUrlSlug " +
            "AND tp.task.urlSlug = :taskUrlSlug " +
            "AND tp.userCourse.user.id = :userId ")
    TaskProgress findByCourseUrlSlugAndTaskUrlSlugAndUserId(String courseUrlSlug, String taskUrlSlug, Long userId);

}
