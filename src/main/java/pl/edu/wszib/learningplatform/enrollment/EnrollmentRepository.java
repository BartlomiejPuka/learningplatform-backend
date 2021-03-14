package pl.edu.wszib.learningplatform.enrollment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.learningplatform.course.CourseEntity;
import pl.edu.wszib.learningplatform.user.User;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<EnrollmentEntity, Long> {

    List<EnrollmentEntity> findEnrollmentEntitiesByUserIdAndEnrollmentType(Long userId, EnrollmentType enrollmentType);

    boolean existsByCourseIdAndUserIdAndEnrollmentType(Long courseId, Long UserId, EnrollmentType enrollmentType);

}
