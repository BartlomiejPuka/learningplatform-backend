package pl.edu.wszib.learningplatform.usercourse.taskprogress;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wszib.learningplatform.course.task.Task;

public interface TaskProgressRepository extends JpaRepository<TaskProgress, Long> {
}
