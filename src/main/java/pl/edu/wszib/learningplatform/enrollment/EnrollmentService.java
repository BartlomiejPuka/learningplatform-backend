package pl.edu.wszib.learningplatform.enrollment;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;


    public EnrollmentEntity updateEnrollment(EnrollmentEntity enrollmentEntity) { return enrollmentRepository.save(enrollmentEntity); }
}
