package pl.edu.wszib.learningplatform.usercourse;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import pl.edu.wszib.learningplatform.course.Course;
import pl.edu.wszib.learningplatform.user.User;
import pl.edu.wszib.learningplatform.usercourse.lessonprogress.LessonProgress;
import pl.edu.wszib.learningplatform.usercourse.taskprogress.TaskProgress;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user_courses")
@Setter
@Getter
public class UserCourse {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private LocalDate purchasedDate;

    private boolean completed;

    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    @OneToMany
    private List<LessonProgress> lessonProgressList;

    @OneToMany
    private List<TaskProgress> taskProgressList;

}
