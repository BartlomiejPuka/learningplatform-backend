package pl.edu.wszib.learningplatform.controllers;

import lombok.experimental.UtilityClass;
import pl.edu.wszib.learningplatform.usercourse.UserCourse;

@UtilityClass
public class EnrolledCourseMapper {

    public EnrolledCourseDto toDto(UserCourse userCourse){
        return EnrolledCourseDto.builder()
                .id(userCourse.getId())
                .courseTitle(userCourse.getCourse().getTitle())
                .build();
    }
}
