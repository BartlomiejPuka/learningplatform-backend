package pl.edu.wszib.learningplatform.course;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.edu.wszib.learningplatform.course.lesson.LessonDto;
import pl.edu.wszib.learningplatform.course.task.TaskDto;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@Builder
public class CourseDetailsDto {
    private Long id;
    private String title;
    private String description;
    private String author;
    private BigDecimal price;
    private String iconUrl;
    private String category;
    private String categoryIconUrl;
    private Integer lessonsCount;
    private Integer tasksCount;
    private List<LessonDto> lessons;
    private List<TaskDto> tasks;

}
