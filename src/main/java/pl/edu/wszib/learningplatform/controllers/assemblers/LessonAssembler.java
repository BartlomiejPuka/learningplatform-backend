package pl.edu.wszib.learningplatform.controllers.assemblers;

import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.learningplatform.controllers.dto.LessonDto;
import pl.edu.wszib.learningplatform.lesson.model.Lesson;

@Service
public class LessonAssembler extends DtoAssembler<Lesson, LessonDto> {

    private final ModelMapper mapper;

    @Autowired
    public LessonAssembler(){
        mapper = new ModelMapper();

        mapper.typeMap(Lesson.class, LessonDto.class).addMappings(m -> {
        });

        mapper.typeMap(LessonDto.class, Lesson.class).addMappings(m -> {
            m.skip(Lesson::setId);
        });
    }

    @Override
    public Lesson toEntity(LessonDto courseDto) { return mapper.map(courseDto, Lesson.class); }

    @NotNull
    @Override
    public LessonDto toDto(Lesson course) { return mapper.map(course, LessonDto.class); }
}
