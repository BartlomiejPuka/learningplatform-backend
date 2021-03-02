package pl.edu.wszib.learningplatform.course;

import org.springframework.stereotype.Service;

@Service
public class UserCourseMapper {

    public UserCourseDto toDto(CourseEntity courseEntity){
        return UserCourseDto.builder()
                .id(courseEntity.getId())
                .title(courseEntity.getTitle())
                .subTitle(courseEntity.getSubTitle())
                .description(courseEntity.getDescription())
                .image(courseEntity.getImage())
                .build();
    }
}
