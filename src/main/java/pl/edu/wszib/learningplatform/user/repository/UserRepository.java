package pl.edu.wszib.learningplatform.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.learningplatform.course.model.Course;
import pl.edu.wszib.learningplatform.user.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query(value = "SELECT c FROM Enrollment e " +
            "JOIN e.course c " +
            "JOIN e.user u " +
            "WHERE u.id = ?1")
    List<Course> findCoursesEnrolledById(long id);
}
