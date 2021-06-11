package pl.edu.wszib.learningplatform.course;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "courses")
@Setter
@Getter
public class Course {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private BigDecimal price;

    @OneToOne
    private CourseCategory category;

    @Embedded
    private CourseDetails details;


}
