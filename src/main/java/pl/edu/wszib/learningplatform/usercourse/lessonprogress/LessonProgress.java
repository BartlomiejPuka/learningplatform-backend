package pl.edu.wszib.learningplatform.usercourse.lessonprogress;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import pl.edu.wszib.learningplatform.course.lesson.Lesson;
import pl.edu.wszib.learningplatform.usercourse.UserCourse;

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
    private UserCourse userCourse;

    private boolean completed;

    private LocalDate completionDate;
}
