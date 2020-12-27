package pl.edu.wszib.learningplatform.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.learningplatform.course.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
