package pl.edu.wszib.learningplatform.courseproducts;


import lombok.Builder;
import lombok.Value;
import pl.edu.wszib.learningplatform.course.lesson.LessonDto;
import pl.edu.wszib.learningplatform.course.scope.CourseScopeDto;
import pl.edu.wszib.learningplatform.course.task.TaskDto;

import java.math.BigDecimal;
import java.util.List;

@Value
@Builder
public class CourseProductDetailsDto {
    Long courseId;
    String courseIconUrl;
    String urlSlug;
    String title;
    String description;
    String author;
    BigDecimal price;
    String iconUrl;
    String category;
    String categoryIconUrl;
    Integer lessonsCount;
    Integer tasksCount;
    String detailedDescription;
    String recipientDescription;
    List<LessonDto> lessons;
    List<TaskDto> tasks;
    List<CourseScopeDto> courseScopes;
    boolean bought;
    boolean inCart;
}
