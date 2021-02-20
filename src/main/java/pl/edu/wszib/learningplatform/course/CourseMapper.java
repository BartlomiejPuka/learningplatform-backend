package pl.edu.wszib.learningplatform.course;

import org.springframework.stereotype.Service;

@Service
public class CourseMapper {

    public CourseDto toDto(CourseEntity courseEntity) {
        CourseTypeEntity courseTypeEntity = courseEntity.getCourseType();
        return CourseDto.builder()
                .title(courseEntity.getTitle())
                .subTitle(courseEntity.getSubTitle())
                .courseType(courseTypeEntity != null ? courseTypeEntity.getName() : null)
                .description(courseEntity.getDescription())
                .build();
    }
}
