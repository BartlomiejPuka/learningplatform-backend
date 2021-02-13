package pl.edu.wszib.learningplatform.subcourse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edu.wszib.learningplatform.course.CourseEntity;
import pl.edu.wszib.learningplatform.lesson.LessonEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "subcourses")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SubCourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    private String title;

    @Lob
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", updatable = false)
    public CourseEntity courseEntity;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id", updatable = false)
    private List<LessonEntity> lessonEntities;
}
