package pl.edu.wszib.learningplatform.subcourse;

import lombok.Getter;
import lombok.Setter;
import pl.edu.wszib.learningplatform.lesson.LessonDto;

import java.util.List;

@Getter
@Setter
public class SubCourseDto {
    private long id;
    private List<LessonDto> lessons;
    private String title;
    private String description;
}

