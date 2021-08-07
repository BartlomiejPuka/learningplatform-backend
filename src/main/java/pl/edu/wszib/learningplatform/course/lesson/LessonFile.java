package pl.edu.wszib.learningplatform.course.lesson;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "lesson_files")
@Getter
public class LessonFile {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String fileUrl;

    @ManyToOne
    public Lesson lesson;
}
