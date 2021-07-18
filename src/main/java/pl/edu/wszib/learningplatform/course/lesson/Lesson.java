package pl.edu.wszib.learningplatform.course.lesson;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import pl.edu.wszib.learningplatform.course.Course;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lessons")
@Setter
@Getter
public class Lesson {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;

    private String title;

    private String description;

    @ManyToOne
    private Course course;

    @OneToMany
    private List<LessonFile> lessonFiles = new ArrayList<>();

}
