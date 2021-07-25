package pl.edu.wszib.learningplatform.courseproducts;

import lombok.experimental.UtilityClass;
import pl.edu.wszib.learningplatform.course.Course;
import pl.edu.wszib.learningplatform.course.CourseDetails;
import pl.edu.wszib.learningplatform.course.lesson.Lesson;
import pl.edu.wszib.learningplatform.course.lesson.LessonDto;
import pl.edu.wszib.learningplatform.course.lesson.LessonMapper;
import pl.edu.wszib.learningplatform.course.scope.CourseScope;
import pl.edu.wszib.learningplatform.course.scope.CourseScopeDto;
import pl.edu.wszib.learningplatform.course.scope.CourseScopeMapper;
import pl.edu.wszib.learningplatform.course.task.Task;
import pl.edu.wszib.learningplatform.course.task.TaskDto;
import pl.edu.wszib.learningplatform.course.task.TaskMapper;
import pl.edu.wszib.learningplatform.enrolledcourse.EnrolledCourse;

import java.util.List;

import static java.util.stream.Collectors.toList;

@UtilityClass
public class CourseProductDetailsMapper {

    public CourseProductDetailsDto toDto(EnrolledCourse enrolledCourse) {
        Course course = enrolledCourse.getCourse();
        CourseDetails courseDetails = course.getDetails();
        List<Lesson> lessons = course.getLessons();
        List<Task> tasks = course.getTasks();
        List<CourseScope> courseScopes = courseDetails.getScopes();

        return CourseProductDetailsDto.builder()
                .bought(enrolledCourse.isBought())
                .inCart(enrolledCourse.isInCart())
                .detailedDescription(courseDetails.getDetailedDescription())
                .recipientDescription(courseDetails.getRecipientDescription())
                .description(courseDetails.getDescription())
                .urlSlug(courseDetails.getUrlSlug())
                .author(courseDetails.getAuthor())
                .courseIconUrl(courseDetails.getIconUrl())

                .courseId(course.getId())
                .category(course.getCategory().getCategory())
                .price(course.getPrice())
                .title(course.getTitle())
                .categoryIconUrl(course.getCategory().getIconUrl())

                .lessonsCount(lessons.size())
                .lessons(extractLessons(lessons))

                .tasksCount(tasks.size())
                .tasks(extractTasks(tasks))

                .courseScopes(extractScopes(courseScopes))
                .build();
    }

    private List<CourseScopeDto> extractScopes(List<CourseScope> scopes){
        return scopes.stream()
                .map(CourseScopeMapper::toDto)
                .collect(toList());
    }

    private List<LessonDto> extractLessons(List<Lesson> lessons) {
        return lessons.stream()
                .map(LessonMapper::toDto)
                .collect(toList());
    }

    private List<TaskDto> extractTasks(List<Task> tasks) {
        return tasks.stream()
                .map(TaskMapper::toDto)
                .collect(toList());
    }
}
