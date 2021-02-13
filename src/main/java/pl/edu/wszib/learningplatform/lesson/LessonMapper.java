package pl.edu.wszib.learningplatform.lesson;

import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.learningplatform.base.DtoMapper;

@Service
public class LessonMapper extends DtoMapper<LessonEntity, LessonDto> {

    private final ModelMapper mapper;

    @Autowired
    public LessonMapper(){
        mapper = new ModelMapper();

        mapper.typeMap(LessonEntity.class, LessonDto.class).addMappings(m -> {
        });

        mapper.typeMap(LessonDto.class, LessonEntity.class).addMappings(m -> {
            m.skip(LessonEntity::setId);
        });
    }

    @Override
    public LessonEntity toEntity(LessonDto courseDto) { return mapper.map(courseDto, LessonEntity.class); }

    @Override
    public LessonDto toDto(LessonEntity course) { return mapper.map(course, LessonDto.class); }
}
