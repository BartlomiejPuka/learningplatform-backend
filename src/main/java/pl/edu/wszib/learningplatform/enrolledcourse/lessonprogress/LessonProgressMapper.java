package pl.edu.wszib.learningplatform.enrolledcourse.lessonprogress;

import lombok.experimental.UtilityClass;
import pl.edu.wszib.learningplatform.course.lesson.Lesson;
import pl.edu.wszib.learningplatform.course.lesson.LessonFile;
import pl.edu.wszib.learningplatform.course.lesson.LessonFileDto;
import pl.edu.wszib.learningplatform.course.lesson.LessonFileMapper;

import java.util.List;

import static java.util.stream.Collectors.toList;

@UtilityClass
public class LessonProgressMapper {
    public LessonProgressDto toDto(LessonProgress lessonProgress) {
        Lesson lesson = lessonProgress.getLesson();
        return LessonProgressDto.builder()
                .id(lessonProgress.getId())
                .title(lesson.getTitle())
                .description(lesson.getDescription())
                .files(extractLessonFiles(lesson.getLessonFiles()))
                .completed(lessonProgress.isCompleted())
                .completionDate(lessonProgress.getCompletionDate())
                .build();
    }

    public List<LessonFileDto> extractLessonFiles(List<LessonFile> lessonFileList){
        return lessonFileList.stream()
                .map(LessonFileMapper::toDto)
                .collect(toList());
    }
}
