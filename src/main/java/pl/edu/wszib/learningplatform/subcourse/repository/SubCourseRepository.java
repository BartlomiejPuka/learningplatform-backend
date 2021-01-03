package pl.edu.wszib.learningplatform.subcourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.learningplatform.subcourse.model.SubCourse;

import java.util.List;

@Repository
public interface SubCourseRepository extends JpaRepository<SubCourse, Long> {


    @Query("SELECT s FROM Course c " +
            "JOIN SubCourse s ON c.id = s.course.id " +
            "WHERE c.id = :cid ")
    public List<SubCourse> GetSubCoursesByCourseId(@Param("cid") Long courseId);
}
