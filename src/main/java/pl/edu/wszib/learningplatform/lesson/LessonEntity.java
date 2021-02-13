package pl.edu.wszib.learningplatform.lesson;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table( name = "lessons")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LessonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    private String title;

    @Lob
    private String description;

    public Long subCourseId;
}
