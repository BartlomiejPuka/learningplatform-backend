package pl.edu.wszib.learningplatform.course;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CourseCategoryMapper {

    public CourseCategoryDto toDto(CourseCategory courseCategory) {
        return CourseCategoryDto.builder()
                .id(courseCategory.getId())
                .category(courseCategory.getCategory())
                .iconUrl(courseCategory.getIconUrl())
                .urlSlug(courseCategory.getUrlSlug())
                .build();
    }
}
