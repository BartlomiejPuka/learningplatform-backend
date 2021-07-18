package pl.edu.wszib.learningplatform.courseproducts;


import lombok.experimental.UtilityClass;
import pl.edu.wszib.learningplatform.course.Course;

@UtilityClass
public class CourseProductMapper {

    public CourseProductDto toDto(ICourseProduct iCourseProduct) {
        return CourseProductDto.builder()
                .courseId(iCourseProduct.getCourseId())
                .isOwned(iCourseProduct.isOwned())
                .author(iCourseProduct.getAuthor())
//                .author(course.getDetails().getAuthor())
//                .category(course.getCategory().getCategory())
//                .description(course.getDetails().getDescription())
//                .price(course.getPrice())
//                .title(course.getTitle())
                .lessonsCount(iCourseProduct.getLessonsCount())
                .tasksCount(iCourseProduct.getTasksCount())
                .build();
    }
}
