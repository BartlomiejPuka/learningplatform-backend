package pl.edu.wszib.learningplatform.course.task;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class TaskDto {
    private Long id;
    private Long orderId;
    private String SEID;
    private String title;
    private String description;
}
