package pl.edu.wszib.learningplatform.usercourse.taskprogress;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "task_progress")
@Setter
@Getter
public class TaskProgress {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
