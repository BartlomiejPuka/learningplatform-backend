package pl.edu.wszib.learningplatform.task;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table( name = "tasks")
@Setter
@Getter
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
}
