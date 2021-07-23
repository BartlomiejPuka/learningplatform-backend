package pl.edu.wszib.learningplatform.enrolledcourse.taskprogress;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskProgressRepository extends JpaRepository<TaskProgress, Long> {
}
