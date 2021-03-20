package pl.edu.wszib.learningplatform.course.module;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ModuleDto {
    private Long Id;
    private String title;
    private String description;
    private Long courseId;
}
