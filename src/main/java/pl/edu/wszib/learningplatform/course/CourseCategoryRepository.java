package pl.edu.wszib.learningplatform.course;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseCategoryRepository extends JpaRepository<CourseCategory, Long> {

    Optional<CourseCategory> findByUrlSlug(String urlSlug);
}
