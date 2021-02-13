package pl.edu.wszib.learningplatform.subcourse;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class SubCourseComponent {

    private final SubCourseService subCourseService;
    private final SubCourseMapper subCourseAssembler;

    public List<SubCourseDto> GetSubCourses(Long courseId){
        List<SubCourseEntity> subCoursesModelEntities = subCourseService.GetSubCoursesByCourseId(courseId);
        return subCoursesModelEntities.stream().map(subCourseAssembler::toDto).collect(toList());
    }
}
