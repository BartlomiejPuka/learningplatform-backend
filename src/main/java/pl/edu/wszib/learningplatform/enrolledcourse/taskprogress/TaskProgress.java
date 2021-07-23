package pl.edu.wszib.learningplatform.enrolledcourse.taskprogress;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import pl.edu.wszib.learningplatform.course.task.Task;
import pl.edu.wszib.learningplatform.enrolledcourse.EnrolledCourse;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "task_progress")
@Setter
@Getter
public class TaskProgress {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Task task;

    @ManyToOne
    private EnrolledCourse userCourse;

    private boolean completed;

    private LocalDate completionDate;

}
