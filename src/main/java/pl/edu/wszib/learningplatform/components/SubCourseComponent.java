package pl.edu.wszib.learningplatform.components;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wszib.learningplatform.controllers.assemblers.SubCourseAssembler;
import pl.edu.wszib.learningplatform.controllers.dto.SubCourseDto;
import pl.edu.wszib.learningplatform.subcourse.model.SubCourse;
import pl.edu.wszib.learningplatform.subcourse.service.SubCourseService;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class SubCourseComponent {

    private final SubCourseService subCourseService;
    private final SubCourseAssembler subCourseAssembler;

    public List<SubCourseDto> GetSubCourses(Long courseId){
        List<SubCourse> subCoursesModels = subCourseService.GetSubCoursesByCourseId(courseId);
        return subCoursesModels.stream().map(subCourseAssembler::toDto).collect(toList());
    }
}
