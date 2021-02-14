package pl.edu.wszib.learningplatform.course;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "course_types")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CourseTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;
}
