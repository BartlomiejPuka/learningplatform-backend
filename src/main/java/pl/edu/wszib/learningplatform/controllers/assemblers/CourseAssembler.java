package pl.edu.wszib.learningplatform.controllers.assemblers;

import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.learningplatform.controllers.dto.CourseDto;
import pl.edu.wszib.learningplatform.course.model.Course;

@Service
public class CourseAssembler extends DtoAssembler<Course, CourseDto> {

    private final ModelMapper mapper;

    @Autowired
    public CourseAssembler(){
        mapper = new ModelMapper();

        mapper.typeMap(Course.class, CourseDto.class).addMappings(m -> {
        });

        mapper.typeMap(CourseDto.class, Course.class).addMappings(m -> {
            m.skip(Course::setId);
        });
    }

    @Override
    public Course toEntity(CourseDto courseDto) { return mapper.map(courseDto, Course.class); }

    @NotNull
    @Override
    public CourseDto toDto(Course course) { return mapper.map(course, CourseDto.class); }
}
