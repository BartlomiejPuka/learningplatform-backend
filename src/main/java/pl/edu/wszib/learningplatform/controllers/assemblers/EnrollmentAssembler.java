package pl.edu.wszib.learningplatform.controllers.assemblers;

import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.learningplatform.controllers.dto.EnrollmentDto;
import pl.edu.wszib.learningplatform.enrollment.model.Enrollment;

@Service
public class EnrollmentAssembler extends DtoAssembler<Enrollment, EnrollmentDto>{

    private final ModelMapper mapper;

    @Autowired
    public EnrollmentAssembler(){
        mapper = new ModelMapper();

        mapper.typeMap(Enrollment.class, EnrollmentDto.class).addMappings(m -> {
            m.map(e -> e.getCourse().getId(), EnrollmentDto::setCourseId);
            m.map(e -> e.getUser().getId(), EnrollmentDto::setUserId);
        });

        mapper.typeMap(EnrollmentDto.class, Enrollment.class).addMappings(m -> {
            m.skip(Enrollment::setId);
        });
    }

    @Override
    public Enrollment toEntity(EnrollmentDto enrollmentDto) { return mapper.map(enrollmentDto, Enrollment.class); }

    @NotNull
    @Override
    public EnrollmentDto toDto(Enrollment enrollment) { return mapper.map(enrollment, EnrollmentDto.class); }
}
