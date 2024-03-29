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

    public List<EnrolledLessonDto> getAllCourseLessons(String courseUrlSlug, User user) {
        List<LessonProgress> lessonProgressList = lessonProgressRepository.findByCourseUrlSlugAndUserId(courseUrlSlug, user.getId());
        return lessonProgressList.stream().map(EnrolledLessonMapper::toDto).collect(toList());
    }

    public EnrolledLessonDetailsDto getCourseLessonDetails(String courseUrlSlug, String lessonUrlSlug, User user) {
        LessonProgress lessonProgress = lessonProgressRepository.findByCourseUrlSlugAndLessonUrlSlugAndUserId(courseUrlSlug, lessonUrlSlug, user.getId());
        return EnrolledLessonDetailsMapper.toDto(lessonProgress);
    }

    public void completeCourseLesson(String courseUrlSlug, String lessonUrlSlug, User user) {
        LessonProgress lessonProgress = lessonProgressRepository.findByCourseUrlSlugAndLessonUrlSlugAndUserId(courseUrlSlug, lessonUrlSlug, user.getId());
        lessonProgress.setCompleted(true);
        lessonProgress.setCompletionDate(LocalDate.now());
        lessonProgressRepository.save(lessonProgress);
    }
}
