package pl.edu.wszib.learningplatform.course;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class CourseDto {
    private Long id;
    private String title;
    private String subTitle;
    private String description;
    private byte[] image;
    private boolean isUserEnrolled;
}
