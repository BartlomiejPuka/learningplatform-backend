package pl.edu.wszib.learningplatform.course;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CourseMapper {

    public CourseDto toDto(Course course) {
        return CourseDto.builder()
                .id(course.getId())
                .title(course.getTitle())
                .author(course.getDetails().getAuthor())
                .description(course.getDetails().getDescription())
                .price(course.getPrice())
                .urlSlug(course.getDetails().getUrlSlug())
                .courseCategoryDto(CourseCategoryMapper.toDto(course.getCategory()))
                .build();
    }
}
