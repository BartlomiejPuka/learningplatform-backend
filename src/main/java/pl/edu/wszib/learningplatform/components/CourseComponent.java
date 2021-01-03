package pl.edu.wszib.learningplatform.components;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.edu.wszib.learningplatform.controllers.assemblers.CourseAssembler;
import pl.edu.wszib.learningplatform.controllers.assemblers.EnrollmentAssembler;
import pl.edu.wszib.learningplatform.controllers.dto.CourseDto;
import pl.edu.wszib.learningplatform.controllers.dto.EnrollmentDto;
import pl.edu.wszib.learningplatform.controllers.dto.StartCourseDto;
import pl.edu.wszib.learningplatform.controllers.exceptions.NotFoundException;
import pl.edu.wszib.learningplatform.course.model.Course;
import pl.edu.wszib.learningplatform.course.service.CourseService;
import pl.edu.wszib.learningplatform.enrollment.model.Enrollment;
import pl.edu.wszib.learningplatform.enrollment.service.EnrollmentService;
import pl.edu.wszib.learningplatform.user.model.User;
import pl.edu.wszib.learningplatform.user.service.UserService;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class CourseComponent {

    private final CourseService courseService;
    private final CourseAssembler courseAssembler;

    private final UserService userService;

    private final EnrollmentService enrollmentService;
    private final EnrollmentAssembler enrollmentAssembler;

    public List<CourseDto> getCourses(){
        List<Course> coursesModels = courseService.findAll();
        return coursesModels.stream().map(courseAssembler::toDto).collect(toList());
    }

    public EnrollmentDto startCourse(StartCourseDto startCourseDto){
        long courseId = startCourseDto.getCourseId();
        long userId = startCourseDto.getUserId();
        courseService.IsUserNotEnrolledToCourse(userId, courseId);
        Course course = courseService.findById(courseId).orElseThrow(
                () -> new NotFoundException("Course with courseId=%s has not been found!")
        );
        User user = userService.findById(userId).orElseThrow(
                () -> new NotFoundException("User with userId=%s has not been found!")
        );
        Enrollment enrollment = new Enrollment();
        enrollment.setCourse(course);
        enrollment.setUser(user);
        enrollment.setEnrollmentDate(Timestamp.from(Instant.now()));
        return enrollmentAssembler.toDto(enrollmentService.updateEnrollment(enrollment));
    }
}
