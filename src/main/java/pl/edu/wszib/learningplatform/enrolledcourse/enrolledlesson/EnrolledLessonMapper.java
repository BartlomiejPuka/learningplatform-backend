package pl.edu.wszib.learningplatform.enrolledcourse.enrolledlesson;

import lombok.experimental.UtilityClass;
import pl.edu.wszib.learningplatform.course.lesson.Lesson;
import pl.edu.wszib.learningplatform.course.lesson.LessonFile;
import pl.edu.wszib.learningplatform.course.lesson.LessonFileDto;
import pl.edu.wszib.learningplatform.course.lesson.LessonFileMapper;
import pl.edu.wszib.learningplatform.enrolledcourse.lessonprogress.LessonProgress;

import java.util.List;

import static java.util.stream.Collectors.toList;

@UtilityClass
public class EnrolledLessonMapper {

    public EnrolledLessonDto toDto(LessonProgress lessonProgress) {
        Lesson lesson = lessonProgress.getLesson();
        return EnrolledLessonDto.builder()
            .lessonId(lesson.getId())
            .completed(lessonProgress.isCompleted())
            .completionDate(lessonProgress.getCompletionDate())
            .orderId(lesson.getOrderId())
            .title(lesson.getTitle())
            .description(lesson.getDescription())
            .lessonFiles(extractLessonFiles(lesson.getLessonFiles()))
            .urlSlug(lesson.getUrlSlug())
            .build();
    }

    private List<LessonFileDto> extractLessonFiles(List<LessonFile> lessonFile){
        return lessonFile.stream().map(LessonFileMapper::toDto).collect(toList());
    }
}
