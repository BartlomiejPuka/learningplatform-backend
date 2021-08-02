package pl.edu.wszib.learningplatform.enrolledcourse.enrolledlesson;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wszib.learningplatform.enrolledcourse.lessonprogress.LessonProgress;
import pl.edu.wszib.learningplatform.enrolledcourse.lessonprogress.LessonProgressRepository;
import pl.edu.wszib.learningplatform.user.User;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class EnrolledLessonService {

    private final LessonProgressRepository lessonProgressRepository;

    public List<EnrolledLessonDto> getAllCourseLessons(Long courseId, User user) {
        List<LessonProgress> lessonProgressList = lessonProgressRepository.findByUserCourseIdAndUserCourseUserId(courseId, user.getId());
        return lessonProgressList.stream().map(EnrolledLessonMapper::toDto).collect(toList());
    }

    public void completeCourseLesson(Long courseId, Long lessonId, User user) {
        LessonProgress lessonProgress = lessonProgressRepository.findByUserCourseIdAndLessonIdAndUserCourseUserId(courseId, lessonId, user.getId());
        lessonProgress.setCompleted(true);
        lessonProgress.setCompletionDate(LocalDate.now());
        lessonProgressRepository.save(lessonProgress);
    }
}
