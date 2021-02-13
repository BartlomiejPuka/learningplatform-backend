package pl.edu.wszib.learningplatform.course;

import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.learningplatform.base.DtoMapper;

@Service
public class CourseMapper extends DtoMapper<CourseEntity, CourseDto> {

    private final ModelMapper mapper;

    @Autowired
    public CourseMapper(){
        mapper = new ModelMapper();

        mapper.typeMap(CourseEntity.class, CourseDto.class).addMappings(m -> {
        });

        mapper.typeMap(CourseDto.class, CourseEntity.class).addMappings(m -> {
            m.skip(CourseEntity::setId);
        });
    }

    @Override
    public CourseEntity toEntity(CourseDto courseDto) { return mapper.map(courseDto, CourseEntity.class); }

    @Override
    public CourseDto toDto(CourseEntity courseEntity) { return mapper.map(courseEntity, CourseDto.class); }
}
