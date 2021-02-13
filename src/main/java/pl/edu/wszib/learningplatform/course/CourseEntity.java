package pl.edu.wszib.learningplatform.course;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edu.wszib.learningplatform.subcourse.SubCourseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "courses")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    private String title;

    @Lob
    private String description;

    @OneToMany(fetch = FetchType.LAZY)
    private List<SubCourseEntity> subCoursEntities;
}
