package pl.edu.wszib.learningplatform.enrolledcourse;


import lombok.Builder;
import lombok.Value;
import pl.edu.wszib.learningplatform.enrolledcourse.lessonprogress.LessonProgressDto;
import pl.edu.wszib.learningplatform.enrolledcourse.taskprogress.TaskProgressDto;

import java.time.LocalDate;
import java.util.List;

@Value
@Builder
public class EnrolledCourseDto {
    Long courseId;
    String courseTitle;
    String courseIconUrl;
    LocalDate purchasedDate;
    boolean completed;
    Long totalLessonsCount;
    Long totalTasksCount;
    Long completedLessonsCount;
    Long completedTasksCount;
    List<LessonProgressDto> lessonProgressList;
    List<TaskProgressDto> taskProgressList;
}
