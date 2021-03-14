package pl.edu.wszib.learningplatform.course;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class CourseExistsByIdValidator implements ConstraintValidator<CourseExistsById, Long> {

    CourseRepository courseRepository;

    public CourseExistsByIdValidator(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public boolean isValid(Long courseId, ConstraintValidatorContext context) {
        return courseRepository.existsById(courseId);
    }
}
