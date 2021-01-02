package pl.edu.wszib.learningplatform.enrollment.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import pl.edu.wszib.learningplatform.course.model.Course;
import pl.edu.wszib.learningplatform.user.model.User;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table( name = "enrollments")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

//    public Long userId;
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", updatable = false)
    public User user;

//    public Long CourseId;

    @CreationTimestamp
    public Timestamp enrollmentDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", updatable = false)
    private Course course;
}
