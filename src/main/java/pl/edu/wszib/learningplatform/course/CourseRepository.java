package pl.edu.wszib.learningplatform.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.learningplatform.enrollment.EnrollmentType;

import java.util.List;
@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Long> {


    @Query("SELECT " +
            "new pl.edu.wszib.learningplatform.course.UserCourseDto(c.id, c.title, c.subTitle, c.description, c.image,  " +
            " case when e.id is null then false else true end) " +
            " from CourseEntity c " +
            "LEFT JOIN EnrollmentEntity e " +
            "ON c.id = e.courseEntity.id and e.user.id = :userId and e.enrollmentType = :enrollmentType ")
    List<UserCourseDto> getUserCourses(@Param("userId") Long userId, @Param("enrollmentType") EnrollmentType enrollmentType);
}
