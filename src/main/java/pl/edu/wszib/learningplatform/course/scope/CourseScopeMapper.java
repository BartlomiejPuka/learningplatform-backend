package pl.edu.wszib.learningplatform.course.scope;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CourseScopeMapper {

    public CourseScopeDto toDto(CourseScope courseScope){
        return CourseScopeDto.builder()
                .id(courseScope.getId())
                .scope(courseScope.getScope())
                .build();
    }
}
