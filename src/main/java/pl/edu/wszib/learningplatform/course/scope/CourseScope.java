package pl.edu.wszib.learningplatform.course.scope;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import pl.edu.wszib.learningplatform.course.Course;

import javax.persistence.*;

@Entity
@Table(name = "course_scopes")
@Setter
@Getter
public class CourseScope {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String scope;

    @ManyToOne
    private Course course;
}
