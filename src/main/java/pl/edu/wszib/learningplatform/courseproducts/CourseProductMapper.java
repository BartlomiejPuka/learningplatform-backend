package pl.edu.wszib.learningplatform.courseproducts;


import lombok.experimental.UtilityClass;
import pl.edu.wszib.learningplatform.course.Course;
import pl.edu.wszib.learningplatform.enrolledcourse.EnrolledCourse;

@UtilityClass
public class CourseProductMapper {

    public CourseProductDto toDto(EnrolledCourse enrolledCourse) {
        Course course = enrolledCourse.getCourse();
        return CourseProductDto.builder()
                .courseId(course.getId())
                .bought(enrolledCourse.isBought())
                .urlSlug(course.getDetails().getUrlSlug())
                .inCart(enrolledCourse.isInCart())
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
