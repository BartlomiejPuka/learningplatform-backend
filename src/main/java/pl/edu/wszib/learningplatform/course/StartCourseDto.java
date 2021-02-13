package pl.edu.wszib.learningplatform.course;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StartCourseDto {
    private Long userId;
    private Long courseId;
}
