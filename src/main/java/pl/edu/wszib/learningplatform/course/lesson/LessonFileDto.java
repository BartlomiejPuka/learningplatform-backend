package pl.edu.wszib.learningplatform.course.lesson;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class LessonFileDto {

    private Long id;
    private String fileUrl;
}
