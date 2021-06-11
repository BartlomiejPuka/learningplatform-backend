package pl.edu.wszib.learningplatform.course;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
public class CourseDto {
    private Long id;
    private String title;
    private String description;
    private String author;
    private BigDecimal price;
    private String category;

}
