package pl.edu.wszib.learningplatform.course;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.edu.wszib.learningplatform.enrollment.EnrollmentMapper;
import pl.edu.wszib.learningplatform.enrollment.EnrollmentDto;
import pl.edu.wszib.learningplatform.util.exceptions.NotFoundException;
import pl.edu.wszib.learningplatform.enrollment.EnrollmentEntity;
import pl.edu.wszib.learningplatform.enrollment.EnrollmentService;
import pl.edu.wszib.learningplatform.user.User;
import pl.edu.wszib.learningplatform.user.UserService;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class CourseComponent {

    private final CourseService courseService;
    private final CourseMapper courseAssembler;

    private final UserService userService;

    private final EnrollmentService enrollmentService;
    private final EnrollmentMapper enrollmentAssembler;

    public List<CourseDto> getCourses(){
        List<CourseEntity> coursesModels = courseService.findAll();
        return coursesModels.stream().map(courseAssembler::toDto).collect(toList());
    }

    public EnrollmentDto startCourse(StartCourseDto startCourseDto){
        long courseId = startCourseDto.getCourseId();
        long userId = startCourseDto.getUserId();
        courseService.IsUserNotEnrolledToCourse(userId, courseId);
        CourseEntity courseEntity = courseService.findById(courseId).orElseThrow(
                () -> new NotFoundException("Course with courseId=%s has not been found!")
        );
        User user = userService.findById(userId).orElseThrow(
                () -> new NotFoundException("User with userId=%s has not been found!")
        );
        EnrollmentEntity enrollmentEntity = new EnrollmentEntity();
        enrollmentEntity.setCourseEntity(courseEntity);
        enrollmentEntity.setUser(user);
        enrollmentEntity.setEnrollmentDate(Timestamp.from(Instant.now()));
        return enrollmentAssembler.toDto(enrollmentService.updateEnrollment(enrollmentEntity));
    }
}
