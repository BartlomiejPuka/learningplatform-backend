package pl.edu.wszib.learningplatform.course;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import pl.edu.wszib.learningplatform.course.lesson.LessonDto;
import pl.edu.wszib.learningplatform.course.lesson.LessonMapper;
import pl.edu.wszib.learningplatform.course.task.TaskDto;
import pl.edu.wszib.learningplatform.course.task.TaskMapper;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Validated
public class CourseService {

    private final CourseRepository courseRepository;

    public List<CourseDto> getCourses(CourseCriteria courseCriteria) {
        Specification<Course> courseSpecification = CourseSpecifications.createSpecification(courseCriteria);
        return courseRepository.findAll(courseSpecification).stream()
                .map(CourseMapper::toDto)
                .collect(toList());
    }

    public Map<String, List<CourseDto>> getCategorizedCourses() {
        return courseRepository.findAll().stream()
                .map(CourseMapper::toDto)
                .collect(groupingBy(CourseDto::getCategory));
    }

    public List<LessonDto> getCourseLessons(Long courseId) {
        return courseRepository.getOne(courseId).getLessons().stream()
                .map(LessonMapper::toDto)
                .collect(toList());
    }

    public List<TaskDto> getCourseTasks(Long courseId) {
        return courseRepository.getOne(courseId).getTasks().stream()
                .map(TaskMapper::toDto)
                .collect(toList());
    }
}
