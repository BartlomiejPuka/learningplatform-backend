package pl.edu.wszib.learningplatform.enrollment;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pl.edu.wszib.learningplatform.course.CourseEntity;
import pl.edu.wszib.learningplatform.user.User;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentEntity updateEnrollment(EnrollmentEntity enrollmentEntity) { return enrollmentRepository.save(enrollmentEntity); }

    public List<EnrollmentEntity> getUserEnrollments(Long userId, EnrollmentType enrollmentType){
        return enrollmentRepository.findEnrollmentEntitiesByUserIdAndEnrollmentType(userId, enrollmentType);
    }

    private boolean checkIfUserAlreadyEnrolled(Long courseId, Long userId, EnrollmentType enrollmentType) {
        return enrollmentRepository.existsByCourseIdAndUserIdAndEnrollmentType(courseId, userId, enrollmentType);
    }
}
