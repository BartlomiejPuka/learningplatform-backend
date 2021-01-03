package pl.edu.wszib.learningplatform.enrollment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pl.edu.wszib.learningplatform.enrollment.model.Enrollment;
import pl.edu.wszib.learningplatform.enrollment.repository.EnrollmentRepository;

@Service
@RequiredArgsConstructor
@Log4j2
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;


    public Enrollment updateEnrollment(Enrollment enrollment) { return enrollmentRepository.save(enrollment); }
}
