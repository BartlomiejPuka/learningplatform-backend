package pl.edu.wszib.learningplatform.course;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.edu.wszib.learningplatform.enrollment.EnrollmentEntity;
import pl.edu.wszib.learningplatform.enrollment.EnrollmentService;
import pl.edu.wszib.learningplatform.enrollment.EnrollmentType;
import pl.edu.wszib.learningplatform.user.User;
import pl.edu.wszib.learningplatform.user.UserService;
import pl.edu.wszib.learningplatform.util.exceptions.ConflictException;
import pl.edu.wszib.learningplatform.user.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static pl.edu.wszib.learningplatform.util.message.MessageTemplates.USER_ALREADY_ENROLLED_TO_COURSE_TEMPLATE;

@Service
@RequiredArgsConstructor
@Log4j2
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final UserRepository userRepository;
    private final EnrollmentService enrollmentService;
    private final UserCourseMapper userCourseMapper;
    private final UserService userService;

    public List<CourseDto> getCourses(){
        List<CourseEntity> courseEntities = courseRepository.findAll();
        return courseEntities.stream().map(courseMapper::toDto).collect(toList());
    }

    public List<UserCourseDto> getUserCourses(String username) {
        User user = userService.findByUsername(username).orElseThrow(
                () -> new EntityNotFoundException(String.format("%s has not been found!",username))
        );
        List<UserCourseDto> userCourses = courseRepository.getUserCourses(user.getId(), EnrollmentType.COURSE);
        return userCourses;
    }

    public void enrollCourse(Long courseId, String username) {
        User user = userService.findByUsername(username).orElseThrow(
                () -> new EntityNotFoundException(String.format("%s has not been found!",username))
        );
        EnrollmentEntity enrollmentEntity = new EnrollmentEntity();
        CourseEntity courseEntity = courseRepository.findById(courseId).orElseThrow(
                () -> new EntityNotFoundException(String.format("There is no course with id=%d", courseId))
        );
        enrollmentEntity.setUser(user);
        enrollmentEntity.setEnrollmentDate(Timestamp.from(Instant.now()));
        enrollmentEntity.setEnrollmentType(EnrollmentType.COURSE);
        enrollmentEntity.setCourseEntity(courseEntity);
        enrollmentService.saveEnrollment(enrollmentEntity);
    }
}
