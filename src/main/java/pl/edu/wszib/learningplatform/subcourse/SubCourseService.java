package pl.edu.wszib.learningplatform.subcourse;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SubCourseService {

    private final SubCourseRepository subCourseRepository;

    public List<SubCourseEntity> GetSubCoursesByCourseId(Long courseId){ return subCourseRepository.GetSubCoursesByCourseId(courseId); }
}
