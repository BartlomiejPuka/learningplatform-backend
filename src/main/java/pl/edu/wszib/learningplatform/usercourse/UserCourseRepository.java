package pl.edu.wszib.learningplatform.usercourse;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCourseRepository extends JpaRepository<UserCourse, Long> {

    boolean existsByCourseIdAndUserId(Long courseId, Long userId);

    UserCourse findByCourseIdAndUserId(Long courseId, Long userId);

    List<UserCourse> findByUserId(Long id);

    List<UserCourse> findByCourseCategoryIdAndUserId(Long courseCategoryId, Long userId);

    List<UserCourse> findByUserIdAndBought(Long userId, boolean bought);
}
