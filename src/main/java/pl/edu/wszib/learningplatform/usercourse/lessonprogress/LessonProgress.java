package pl.edu.wszib.learningplatform.usercourse.lessonprogress;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "lesson_progress")
@Setter
@Getter
public class LessonProgress {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
