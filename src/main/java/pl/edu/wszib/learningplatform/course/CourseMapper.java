package pl.edu.wszib.learningplatform.course;

import org.springframework.stereotype.Service;

@Service
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
