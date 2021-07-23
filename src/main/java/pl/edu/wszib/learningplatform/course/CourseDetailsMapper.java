package pl.edu.wszib.learningplatform.course;

import lombok.experimental.UtilityClass;
import pl.edu.wszib.learningplatform.course.lesson.Lesson;
import pl.edu.wszib.learningplatform.course.lesson.LessonDto;
import pl.edu.wszib.learningplatform.course.lesson.LessonMapper;
import pl.edu.wszib.learningplatform.course.task.Task;
import pl.edu.wszib.learningplatform.course.task.TaskDto;
import pl.edu.wszib.learningplatform.course.task.TaskMapper;

import java.util.List;

import static java.util.stream.Collectors.toList;

@UtilityClass
public class CourseDetailsMapper {

    public CourseDetailsDto toDto(Course course){
        CourseDetails courseDetails = course.getDetails();
        CourseCategory courseCategory = course.getCategory();
        List<Task> tasks = course.getTasks();
        List<Lesson> lessons = course.getLessons();
        return CourseDetailsDto.builder()
                .id(course.getId())
                .title(course.getTitle())
                .price(course.getPrice())
                .lessonsCount(lessons.size())
                .tasksCount(tasks.size())
                .lessons(extractLessons(lessons))
                .tasks(extractTasks(tasks))
                .author(courseDetails.getAuthor())
                .iconUrl(courseDetails.getIconUrl())
                .description(courseDetails.getDescription())
                .category(courseCategory.getCategory())
                .categoryIconUrl(courseCategory.getIconUrl())
                .build();
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
