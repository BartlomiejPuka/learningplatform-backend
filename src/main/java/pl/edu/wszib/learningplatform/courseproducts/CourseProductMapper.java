package pl.edu.wszib.learningplatform.courseproducts;


import lombok.experimental.UtilityClass;
import pl.edu.wszib.learningplatform.course.Course;
import pl.edu.wszib.learningplatform.course.lesson.Lesson;
import pl.edu.wszib.learningplatform.course.task.Task;
import pl.edu.wszib.learningplatform.usercourse.UserCourse;

import java.util.List;

@UtilityClass
public class CourseProductMapper {

    public CourseProductDto toDto(UserCourse userCourse) {
        Course course = userCourse.getCourse();
        return CourseProductDto.builder()
                .courseId(course.getId())
                .bought(userCourse.isBought())
                .inCart(userCourse.isInCart())
                .author(course.getDetails().getAuthor())
                .category(course.getCategory().getCategory())
                .description(course.getDetails().getDescription())
                .price(course.getPrice())
                .title(course.getTitle())
                .lessonsCount(course.getLessons().size())
                .tasksCount(course.getTasks().size())
                .courseIconUrl(course.getDetails().getIconUrl())
                .categoryIconUrl(course.getCategory().getIconUrl())
                .build();
    }
}
