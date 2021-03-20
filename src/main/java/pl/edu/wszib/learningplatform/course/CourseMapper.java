package pl.edu.wszib.learningplatform.course;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CourseMapper {

    public CourseDto toDto(CourseEntity courseEntity) {
        return CourseDto.builder()
                .id(courseEntity.getId())
                .title(courseEntity.getTitle())
                .subTitle(courseEntity.getSubTitle())
                .description(courseEntity.getDescription())
                .image(courseEntity.getImage())
                .build();
    }
}
