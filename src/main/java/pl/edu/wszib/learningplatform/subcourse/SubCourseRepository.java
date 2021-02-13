package pl.edu.wszib.learningplatform.subcourse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCourseRepository extends JpaRepository<SubCourseEntity, Long> {


    @Query("SELECT s FROM CourseEntity c " +
            "JOIN SubCourseEntity s ON c.id = s.courseEntity.id " +
            "WHERE c.id = :cid ")
    public List<SubCourseEntity> GetSubCoursesByCourseId(@Param("cid") Long courseId);
}
