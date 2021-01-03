package pl.edu.wszib.learningplatform.subcourse.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edu.wszib.learningplatform.course.model.Course;
import pl.edu.wszib.learningplatform.lesson.model.Lesson;

import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "subcourses")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SubCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    private String title;

    @Lob
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", updatable = false)
    public Course course;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id", updatable = false)
    private List<Lesson> lessons;
}
