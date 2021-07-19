package pl.edu.wszib.learningplatform.course;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "course_categories")
@Getter
public class CourseCategory {
    @Id
    private Long id;
    private String category;
    private String iconUrl;
}
