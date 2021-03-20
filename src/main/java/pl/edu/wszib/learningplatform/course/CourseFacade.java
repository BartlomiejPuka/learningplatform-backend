package pl.edu.wszib.learningplatform.course;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import pl.edu.wszib.learningplatform.enrollment.EnrollmentEntity;
import pl.edu.wszib.learningplatform.enrollment.EnrollmentRepository;
import pl.edu.wszib.learningplatform.enrollment.EnrollmentService;
import pl.edu.wszib.learningplatform.enrollment.EnrollmentType;
import pl.edu.wszib.learningplatform.user.User;
import pl.edu.wszib.learningplatform.user.UserRepository;

import java.sql.Timestamp;
import java.time.Instant;

@Service
@RequiredArgsConstructor
@Validated
public class CourseFacade {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;

    public void enrollCourse(@CourseExistsById Long courseId, Long userId) {
        CourseEntity courseEntity = courseRepository.getOne(courseId);
        User user = userRepository.getOne(userId);
        EnrollmentEntity enrollmentEntity = new EnrollmentEntity();
        enrollmentEntity.setUser(user);
        enrollmentEntity.setEnrollmentDate(Timestamp.from(Instant.now()));
        enrollmentEntity.setEnrollmentType(EnrollmentType.COURSE);
        enrollmentEntity.setCourse(courseEntity);
        enrollmentRepository.save(enrollmentEntity);
    }

}
