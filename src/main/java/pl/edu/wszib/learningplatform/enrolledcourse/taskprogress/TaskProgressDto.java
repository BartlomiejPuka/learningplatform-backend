package pl.edu.wszib.learningplatform.enrolledcourse.taskprogress;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class TaskProgressDto {
    Long id;
    Long orderId;
    String SEID;
    String title;
    String description;
    boolean completed;
    LocalDate completionDate;
}
