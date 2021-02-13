package pl.edu.wszib.learningplatform.subcourse;

import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.learningplatform.base.DtoMapper;

@Service
public class SubCourseMapper extends DtoMapper<SubCourseEntity, SubCourseDto> {

    private final ModelMapper mapper;

    @Autowired
    public SubCourseMapper(){
        mapper = new ModelMapper();

        mapper.typeMap(SubCourseEntity.class, SubCourseDto.class).addMappings(m -> {
        });

        mapper.typeMap(SubCourseDto.class, SubCourseEntity.class).addMappings(m -> {
            m.skip(SubCourseEntity::setId);
        });
    }

    @Override
    public SubCourseEntity toEntity(SubCourseDto subCourseDto) { return mapper.map(subCourseDto, SubCourseEntity.class); }

    @Override
    public SubCourseDto toDto(SubCourseEntity subCourseEntity) { return mapper.map(subCourseEntity, SubCourseDto.class); }
}