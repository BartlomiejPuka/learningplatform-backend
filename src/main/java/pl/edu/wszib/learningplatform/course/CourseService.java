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
        List<Course> courseEntities = courseRepository.findAll(courseSpecification);
        return courseEntities.stream().map(CourseMapper::toDto).collect(toList());
    }

    public Map<String, List<CourseDto>> getCategorizedCourses() {
        List<Course> courseEntities = courseRepository.findAll();
        return courseEntities.stream()
                .map(CourseMapper::toDto)
                .collect(groupingBy(CourseDto::getCategory));
    }

    public List<LessonDto> getCourseLessons(Long courseId) {
        Course course = courseRepository.getOne(courseId);
        return course.getLessons().stream().map(LessonMapper::toDto).collect(toList());
    }

    public List<TaskDto> getCourseTasks(Long courseId) {
        Course course = courseRepository.getOne(courseId);
        return course.getTasks().stream().map(TaskMapper::toDto).collect(toList());
    }
}
