package pl.edu.wszib.learningplatform.enrolledcourse.taskprogress;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskProgressRepository extends JpaRepository<TaskProgress, Long> {

    List<TaskProgress> findByUserCourseIdAndUserCourseUserId(Long courseId, Long userId);


    TaskProgress findByUserCourseIdAndTaskOrderIdAndUserCourseUserId(Long courseId, Long taskOrderId, Long userId);

}
