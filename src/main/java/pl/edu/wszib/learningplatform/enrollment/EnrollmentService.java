package pl.edu.wszib.learningplatform.enrollment;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pl.edu.wszib.learningplatform.course.CourseEntity;
import pl.edu.wszib.learningplatform.user.User;

@Service
@RequiredArgsConstructor
@Log4j2
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;


    public EnrollmentEntity updateEnrollment(EnrollmentEntity enrollmentEntity) { return enrollmentRepository.save(enrollmentEntity); }


    public boolean checkIfUserIsAssignedToCourse(CourseEntity courseEntity, User user){
        return enrollmentRepository.existsByCourseEntityAndUserAndEnrollmentType(courseEntity, user, EnrollmentType.COURSE);
    }
}
