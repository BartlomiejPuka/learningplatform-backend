package pl.edu.wszib.learningplatform.courseproducts;


import lombok.experimental.UtilityClass;
import pl.edu.wszib.learningplatform.course.Course;
import pl.edu.wszib.learningplatform.course.CourseDetails;
import pl.edu.wszib.learningplatform.course.lesson.Lesson;
import pl.edu.wszib.learningplatform.course.task.Task;
import pl.edu.wszib.learningplatform.enrolledcourse.EnrolledCourse;

import java.util.List;

@UtilityClass
public class CourseProductMapper {

    public CourseProductDto toDto(EnrolledCourse enrolledCourse) {
        Course course = enrolledCourse.getCourse();
        CourseDetails courseDetails = course.getDetails();
        List<Lesson> lessons = course.getLessons();
        List<Task> tasks = course.getTasks();

        return CourseProductDto.builder()
                .bought(enrolledCourse.isBought())
                .inCart(enrolledCourse.isInCart())

                .urlSlug(courseDetails.getUrlSlug())
                .description(courseDetails.getDescription())
                .courseIconUrl(courseDetails.getIconUrl())
                .author(courseDetails.getAuthor())

                .courseId(course.getId())
                .category(course.getCategory().getCategory())
                .price(course.getPrice())
                .title(course.getTitle())
                .lessonsCount(lessons.size())
                .tasksCount(tasks.size())
                .categoryIconUrl(course.getCategory().getIconUrl())
                .build();
    }
}
