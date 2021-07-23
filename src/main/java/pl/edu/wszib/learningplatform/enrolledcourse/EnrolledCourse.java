package pl.edu.wszib.learningplatform.enrolledcourse;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import pl.edu.wszib.learningplatform.course.Course;
import pl.edu.wszib.learningplatform.user.User;
import pl.edu.wszib.learningplatform.enrolledcourse.lessonprogress.LessonProgress;
import pl.edu.wszib.learningplatform.enrolledcourse.taskprogress.TaskProgress;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "enrolled_courses")
@Setter
@Getter
public class EnrolledCourse {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate purchasedDate;

    private boolean inCart;

    private boolean bought;

    private boolean completed;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    @OneToMany(cascade = CascadeType.ALL)
    private List<LessonProgress> lessonProgressList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<TaskProgress> taskProgressList = new ArrayList<>();

    public LessonProgress addLessonProgress(LessonProgress lessonProgress) {
        lessonProgress.setUserCourse(this);
        lessonProgressList.add(lessonProgress);
        return lessonProgress;
    }

    public TaskProgress addTaskProgress(TaskProgress taskProgress) {
        taskProgress.setUserCourse(this);
        taskProgressList.add(taskProgress);
        return taskProgress;
    }
}
