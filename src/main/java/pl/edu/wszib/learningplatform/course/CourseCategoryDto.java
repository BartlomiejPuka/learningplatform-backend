package pl.edu.wszib.learningplatform.course;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class CourseCategoryDto {

    private String category;
    private Long id;
    private String iconUrl;

}
