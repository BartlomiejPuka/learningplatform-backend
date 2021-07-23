package pl.edu.wszib.learningplatform.course.lesson;


import lombok.experimental.UtilityClass;

import java.util.List;

import static java.util.stream.Collectors.toList;

@UtilityClass
public class LessonMapper {

    public LessonDto toDto(Lesson lesson){

        return LessonDto.builder()
                .id(lesson.getId())
                .description(lesson.getDescription())
                .title(lesson.getTitle())
                .orderId(lesson.getOrderId())
                .files(extractFiles(lesson.getLessonFiles()))
                .build();
    }

    private List<LessonFileDto> extractFiles(List<LessonFile> files){
        return files.stream()
                .map(LessonFileMapper::toDto)
                .collect(toList());
    }
}
