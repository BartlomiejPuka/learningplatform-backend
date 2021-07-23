package pl.edu.wszib.learningplatform.enrolledcourse.lessonprogress;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import pl.edu.wszib.learningplatform.course.lesson.Lesson;
import pl.edu.wszib.learningplatform.enrolledcourse.EnrolledCourse;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "lesson_progress")
@Setter
@Getter
public class LessonProgress {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Lesson lesson;

    @ManyToOne(cascade = CascadeType.ALL)
    private EnrolledCourse userCourse;

    private boolean completed;

    private LocalDate completionDate;
}
