package pl.edu.wszib.learningplatform.course.module;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class ModuleService {

    private final ModuleRepository moduleRepository;

    public List<ModuleDto> getModulesByCourseId(Long courseId) {
        List<ModuleEntity> moduleEntities = moduleRepository.findAllByCourseId(courseId);
        return moduleEntities.stream().map(ModuleMapper::toDto).collect(toList());
    }
}
