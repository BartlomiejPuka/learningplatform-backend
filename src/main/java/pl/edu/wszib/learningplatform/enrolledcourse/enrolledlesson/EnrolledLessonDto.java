package pl.edu.wszib.learningplatform.enrolledcourse.enrolledlesson;

import lombok.Builder;
import lombok.Value;
import pl.edu.wszib.learningplatform.course.lesson.LessonFileDto;

import java.time.LocalDate;
import java.util.List;

@Value
@Builder
public class EnrolledLessonDto {
    Long lessonId;
    boolean completed;
    LocalDate completionDate;
    Long orderId;
    String title;
    String description;
    List<LessonFileDto> lessonFiles;
}
