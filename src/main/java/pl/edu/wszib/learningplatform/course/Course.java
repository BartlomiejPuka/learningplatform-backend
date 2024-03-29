package pl.edu.wszib.learningplatform.course;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import pl.edu.wszib.learningplatform.course.lesson.Lesson;
import pl.edu.wszib.learningplatform.course.scope.CourseScope;
import pl.edu.wszib.learningplatform.course.task.Task;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
@Setter
@Getter
public class Course {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private BigDecimal price;

    @OneToOne
    private CourseCategory category;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(inverseJoinColumns=@JoinColumn(name="lesson_id"))
    private List<Lesson> lessons = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(inverseJoinColumns=@JoinColumn(name="task_id"))
    private List<Task> tasks = new ArrayList<>();

    @Embedded
    private CourseDetails details;


}
