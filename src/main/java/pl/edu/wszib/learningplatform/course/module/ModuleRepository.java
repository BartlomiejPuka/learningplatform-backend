package pl.edu.wszib.learningplatform.course.module;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModuleRepository extends JpaRepository<ModuleEntity, Long> {

    List<ModuleEntity> findAllByCourseId(Long courseId);
}
