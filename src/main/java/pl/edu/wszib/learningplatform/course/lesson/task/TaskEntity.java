package pl.edu.wszib.learningplatform.course.lesson.task;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table( name = "tasks")
@Setter
@Getter
public class TaskEntity {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
}
