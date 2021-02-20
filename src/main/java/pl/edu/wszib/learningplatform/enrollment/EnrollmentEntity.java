package pl.edu.wszib.learningplatform.enrollment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import pl.edu.wszib.learningplatform.course.CourseEntity;
import pl.edu.wszib.learningplatform.user.User;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table( name = "enrollments")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", updatable = false)
    public User user;

    @CreationTimestamp
    public Timestamp enrollmentDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", updatable = false)
    private CourseEntity courseEntity;

    @Enumerated(EnumType.STRING)
    private EnrollmentType enrollmentType;
}
