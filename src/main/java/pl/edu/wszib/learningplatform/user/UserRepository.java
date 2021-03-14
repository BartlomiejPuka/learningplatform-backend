package pl.edu.wszib.learningplatform.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.learningplatform.course.CourseEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query(value = "SELECT c FROM EnrollmentEntity e " +
            "JOIN e.course c " +
            "WHERE e.user.id = :uid")
    List<CourseEntity> findCoursesEnrolledById(@Param("uid") Long userId);


    @Query(value = "SELECT u FROM EnrollmentEntity e " +
            "JOIN e.user u " +
            "WHERE e.course.id = :cid")
    List<User> findAllUsersByCourseId(@Param("cid") Long courseId);
}
