package pl.edu.wszib.learningplatform.cart;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.edu.wszib.learningplatform.authentication.service.CustomUser;
import pl.edu.wszib.learningplatform.enrolledcourse.EnrolledCourse;
import pl.edu.wszib.learningplatform.enrolledcourse.EnrolledCourseRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class NotOwnedCourseValidator implements ConstraintValidator<NotOwnedCourse, Long> {

    private final EnrolledCourseRepository userCourseRepository;

    public NotOwnedCourseValidator(EnrolledCourseRepository userCourseRepository) {
        this.userCourseRepository = userCourseRepository;
    }

    @Override
    public boolean isValid(Long courseId, ConstraintValidatorContext context) {
        CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        EnrolledCourse userCourse = userCourseRepository.findByCourseIdAndUserId(courseId, customUser.getId());
        return !userCourse.isBought();
    }
}
