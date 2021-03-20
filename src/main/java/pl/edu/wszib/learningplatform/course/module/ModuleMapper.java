package pl.edu.wszib.learningplatform.course.module;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ModuleMapper {

    public ModuleDto toDto(ModuleEntity moduleEntity) {
        return ModuleDto.builder()
                .Id(moduleEntity.getId())
                .description(moduleEntity.getDescription())
                .title(moduleEntity.getTitle())
                .courseId(moduleEntity.getCourseId())
                .build();
    }
}
