package pl.edu.wszib.learningplatform.course.lesson;


import lombok.experimental.UtilityClass;

@UtilityClass
public class LessonMapper {

    public LessonDto toDto(Lesson lesson){
        return LessonDto.builder()
                .id(lesson.getId())
                .description(lesson.getDescription())
                .title(lesson.getTitle())
                .orderId(lesson.getOrderId())
                .build();
    }
}
