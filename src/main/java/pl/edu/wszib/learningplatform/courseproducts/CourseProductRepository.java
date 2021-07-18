package pl.edu.wszib.learningplatform.courseproducts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.edu.wszib.learningplatform.course.Course;

import java.util.List;

public interface CourseProductRepository extends JpaRepository<Course, Long> {

    @Query("select " +
            "c.id as courseId, " +
            "c.details.author as author," +
            "(uc.id is not null) as owned, " +
            "c.lessons.size as lessonsCount, " +
            "c.tasks.size as tasksCount " +
            "from Course c " +
            "left join UserCourse uc " +
            "on c.id = uc.course.id and " +
            "uc.user.id = :userId ")
    List<ICourseProduct> getCourseProducts(Long userId);
}
