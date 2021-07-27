package pl.edu.wszib.learningplatform.enrolledcourse;

import lombok.experimental.UtilityClass;
import pl.edu.wszib.learningplatform.course.Course;
import pl.edu.wszib.learningplatform.course.CourseDetails;
import pl.edu.wszib.learningplatform.course.lesson.Lesson;
import pl.edu.wszib.learningplatform.course.task.Task;
import pl.edu.wszib.learningplatform.enrolledcourse.lessonprogress.LessonProgress;
import pl.edu.wszib.learningplatform.enrolledcourse.lessonprogress.LessonProgressDto;
import pl.edu.wszib.learningplatform.enrolledcourse.lessonprogress.LessonProgressMapper;
import pl.edu.wszib.learningplatform.enrolledcourse.taskprogress.TaskProgress;
import pl.edu.wszib.learningplatform.enrolledcourse.taskprogress.TaskProgressDto;
import pl.edu.wszib.learningplatform.enrolledcourse.taskprogress.TaskProgressMapper;

import java.util.List;

import static java.util.stream.Collectors.toList;

@UtilityClass
public class EnrolledCourseMapper {

    public EnrolledCourseDto toDto(EnrolledCourse enrolledCourse) {
        Course course = enrolledCourse.getCourse();
        CourseDetails courseDetails = course.getDetails();
        List<Lesson> lessonList = course.getLessons();
        List<Task> taskList = course.getTasks();
        return EnrolledCourseDto.builder()
                .courseId(course.getId())
                .courseTitle(course.getTitle())
                .courseIconUrl(courseDetails.getIconUrl())
                .purchasedDate(enrolledCourse.getPurchasedDate())
                .completed(enrolledCourse.isCompleted())
                .totalLessonsCount((long) lessonList.size())
                .totalTasksCount((long) taskList.size())
                .completedLessonsCount(getCompletedLessonsCount(enrolledCourse.getLessonProgressList()))
                .completedTasksCount(getCompletedTaskCount(enrolledCourse.getTaskProgressList()))
                .lessonProgressList(extractLessonProgress(enrolledCourse.getLessonProgressList()))
                .taskProgressList(extractTaskProgress(enrolledCourse.getTaskProgressList()))

                .lessonsProgressPercentage(calculateLessonsProgressPercentage(enrolledCourse.getLessonProgressList()))
                .tasksProgressPercentage(calculateTasksProgressPercentage(enrolledCourse.getTaskProgressList()))
                .build();
    }

    private Double calculateLessonsProgressPercentage(List<LessonProgress> lessonProgress) {
        int total = lessonProgress.size();
        long completed = lessonProgress.stream()
                .filter(LessonProgress::isCompleted).count();
        if (completed == 0) {
            return 0d;
        }
        return (double) ((total / completed)*100);
    }

    private Double calculateTasksProgressPercentage(List<TaskProgress> taskProgress) {
        int total = taskProgress.size();
        long completed = taskProgress.stream()
                .filter(TaskProgress::isCompleted).count();
        if (completed == 0) {
            return 0d;
        }
        return (double) ((total / completed)*100);
    }

    private Long getCompletedTaskCount(List<TaskProgress> taskProgress){
        return taskProgress.stream()
                .filter(TaskProgress::isCompleted)
                .count();
    }

    private Long getCompletedLessonsCount(List<LessonProgress> lessonProgress){
        return lessonProgress.stream()
                .filter(LessonProgress::isCompleted)
                .count();
    }

    private List<LessonProgressDto> extractLessonProgress(List<LessonProgress> lessonProgress){
        return lessonProgress.stream()
                .map(LessonProgressMapper::toDto)
                .collect(toList());
    }

    private List<TaskProgressDto> extractTaskProgress(List<TaskProgress> taskProgress){
        return taskProgress.stream()
                .map(TaskProgressMapper::toDto)
                .collect(toList());
    }
}
