package pl.edu.wszib.learningplatform.controllers.assemblers;

import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.learningplatform.controllers.dto.SubCourseDto;
import pl.edu.wszib.learningplatform.subcourse.model.SubCourse;

@Service
public class SubCourseAssembler extends DtoAssembler<SubCourse, SubCourseDto> {

    private final ModelMapper mapper;

    @Autowired
    public SubCourseAssembler(){
        mapper = new ModelMapper();

        mapper.typeMap(SubCourse.class, SubCourseDto.class).addMappings(m -> {
        });

        mapper.typeMap(SubCourseDto.class, SubCourse.class).addMappings(m -> {
            m.skip(SubCourse::setId);
        });
    }

    @Override
    public SubCourse toEntity(SubCourseDto subCourseDto) { return mapper.map(subCourseDto, SubCourse.class); }

    @NotNull
    @Override
    public SubCourseDto toDto(SubCourse subCourse) { return mapper.map(subCourse, SubCourseDto.class); }
}