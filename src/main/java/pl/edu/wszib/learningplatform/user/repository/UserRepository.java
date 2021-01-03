package pl.edu.wszib.learningplatform.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.learningplatform.course.model.Course;
import pl.edu.wszib.learningplatform.subcourse.Model.SubCourse;
import pl.edu.wszib.learningplatform.user.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query(value = "SELECT c FROM Enrollment e " +
            "JOIN e.course c " +
            "WHERE e.user.id = :uid")
    List<Course> findCoursesEnrolledById(@Param("uid") long userId);


    @Query(value = "SELECT s FROM Enrollment e " +
            "JOIN e.course c " +
            "JOIN c.subCourses s " +
            "WHERE e.user.id = :uid")
    List<SubCourse> findSubCoursesEnrolledById(@Param("uid") long userId);


    @Query(value = "SELECT u FROM Enrollment e " +
            "JOIN e.user u " +
            "WHERE e.course.id = :cid")
    List<User> findAllUsersByCourseId(@Param("cid") long courseId);
}
