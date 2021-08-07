package pl.edu.wszib.learningplatform.enrolledcourse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EnrolledCourseRepository extends JpaRepository<EnrolledCourse, Long> {

    boolean existsByCourseIdAndUserId(Long courseId, Long userId);

    EnrolledCourse findByCourseIdAndUserId(Long courseId, Long userId);

    @Query("SELECT ec FROM EnrolledCourse ec " +
            "WHERE ec.course.details.urlSlug = :courseUrlSlug " +
            "AND ec.user.id = :userId ")
    Optional<EnrolledCourse> findByCourseUrlSlugAndUserId(String courseUrlSlug, Long userId);

    Optional<EnrolledCourse> findByCourseDetailsUrlSlugAndUserId(String urlSlug, Long userId);

    List<EnrolledCourse> findByUserId(Long id);

    List<EnrolledCourse> findByCourseCategoryIdAndUserId(Long courseCategoryId, Long userId);

    List<EnrolledCourse> findByCourseCategoryUrlSlugAndUserId(String urlSlug, Long userId);

    List<EnrolledCourse> findByUserIdAndBought(Long userId, boolean bought);

}
