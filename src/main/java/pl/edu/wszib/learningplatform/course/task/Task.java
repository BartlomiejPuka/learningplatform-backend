package pl.edu.wszib.learningplatform.course.task;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import pl.edu.wszib.learningplatform.course.Course;

import javax.persistence.*;

@Entity
@Table(name = "tasks")
@Setter
@Getter
public class Task {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;

    private String seid;

    private String title;

    private String description;

    private String urlSlug;

    @ManyToOne
    private Course course;
}
