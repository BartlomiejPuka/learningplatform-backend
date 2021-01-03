package pl.edu.wszib.learningplatform.controllers.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SubCourseDto {
    private long id;
    private List<LessonDto> lessons;
    private String title;
    private String description;
}

