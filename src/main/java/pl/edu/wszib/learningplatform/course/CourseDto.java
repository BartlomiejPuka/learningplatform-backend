package pl.edu.wszib.learningplatform.course;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CourseDto {
    private Long id;
    private String title;
    private String subTitle;
    private String description;
    private byte[] image;
}
