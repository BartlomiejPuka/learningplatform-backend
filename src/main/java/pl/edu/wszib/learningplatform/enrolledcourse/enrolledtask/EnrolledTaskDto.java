package pl.edu.wszib.learningplatform.enrolledcourse.enrolledtask;


import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class EnrolledTaskDto {

    boolean completed;
    LocalDate completionDate;
    Long orderId;
    String SEID;
    String title;
    String description;
}
