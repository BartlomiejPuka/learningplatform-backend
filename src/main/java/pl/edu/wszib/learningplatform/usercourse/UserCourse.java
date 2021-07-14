package pl.edu.wszib.learningplatform.usercourse;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import pl.edu.wszib.learningplatform.course.Course;
import pl.edu.wszib.learningplatform.user.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user_courses")
@Setter
@Getter
public class UserCourse {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate startDate;

    private boolean completed;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

}
