package pl.edu.wszib.learningplatform.enrolledcourse.lessonprogress;

import lombok.Builder;
import lombok.Value;
import pl.edu.wszib.learningplatform.course.lesson.LessonFileDto;

import java.time.LocalDate;
import java.util.List;

@Value
@Builder
public class LessonProgressDto {
    Long id;
    Long orderId;
    String title;
    String description;
    List<LessonFileDto> files;
    boolean completed;
    LocalDate completionDate;
}
