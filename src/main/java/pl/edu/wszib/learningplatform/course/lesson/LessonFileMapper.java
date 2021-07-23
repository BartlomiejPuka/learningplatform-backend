package pl.edu.wszib.learningplatform.course.lesson;

import lombok.experimental.UtilityClass;

@UtilityClass
public class LessonFileMapper {

    public LessonFileDto toDto(LessonFile lessonFile) {
        return LessonFileDto.builder()
                .id(lessonFile.getId())
                .fileUrl(lessonFile.getFileUrl())
                .build();
    }
}
