package pl.edu.wszib.learningplatform.courseproducts;


import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class CourseProductDto {
    Long courseId;
    String title;
    String description;
    String author;
    BigDecimal price;
    String category;
    Integer lessonsCount;
    Integer tasksCount;
    boolean isOwned;
}
