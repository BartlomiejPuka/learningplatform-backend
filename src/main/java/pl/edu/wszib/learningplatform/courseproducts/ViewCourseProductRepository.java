package pl.edu.wszib.learningplatform.courseproducts;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ViewCourseProductRepository extends JpaRepository<ViewCourseProduct, Long> {

    List<ViewCourseProduct> findByUserId(Long userId);
}
