package pl.edu.wszib.learningplatform.subcourse.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wszib.learningplatform.subcourse.model.SubCourse;
import pl.edu.wszib.learningplatform.subcourse.repository.SubCourseRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class SubCourseService {

    private final SubCourseRepository subCourseRepository;

    public List<SubCourse> GetSubCoursesByCourseId(Long courseId){ return subCourseRepository.GetSubCoursesByCourseId(courseId); }
}
