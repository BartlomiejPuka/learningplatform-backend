package pl.edu.wszib.learningplatform.enrolledcourse.enrolledtask;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class EnrolledTaskDetailsDto {
    boolean completed;
    LocalDate completionDate;
    Long orderId;
    String seid;
    String title;
    String description;

    String taskUrlSlug;
    String courseUrlSlug;
}
