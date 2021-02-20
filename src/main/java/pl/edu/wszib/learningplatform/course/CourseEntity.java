package pl.edu.wszib.learningplatform.course;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "courses")
@Setter
@Getter
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    private String title;
    private String subTitle;

    @ManyToOne(fetch = FetchType.LAZY)
    private CourseTypeEntity courseType;

    @Lob
    private String description;
}
