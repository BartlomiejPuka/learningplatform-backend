package pl.edu.wszib.learningplatform.enrollment;

import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.learningplatform.base.DtoMapper;

@Service
public class EnrollmentMapper extends DtoMapper<EnrollmentEntity, EnrollmentDto> {

    private final ModelMapper mapper;

    @Autowired
    public EnrollmentMapper(){
        mapper = new ModelMapper();

        mapper.typeMap(EnrollmentEntity.class, EnrollmentDto.class).addMappings(m -> {
            m.map(e -> e.getCourseEntity().getId(), EnrollmentDto::setCourseId);
            m.map(e -> e.getUser().getId(), EnrollmentDto::setUserId);
        });

        mapper.typeMap(EnrollmentDto.class, EnrollmentEntity.class).addMappings(m -> {
            m.skip(EnrollmentEntity::setId);
        });
    }

    @Override
    public EnrollmentEntity toEntity(EnrollmentDto enrollmentDto) { return mapper.map(enrollmentDto, EnrollmentEntity.class); }

    @Override
    public EnrollmentDto toDto(EnrollmentEntity enrollmentEntity) { return mapper.map(enrollmentEntity, EnrollmentDto.class); }
}
