package pl.edu.wszib.learningplatform.course.lesson;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class LessonDto {

    private Long id;
    private Long orderId;
    private String title;
    private String description;
    private List<LessonFileDto> files;
}
