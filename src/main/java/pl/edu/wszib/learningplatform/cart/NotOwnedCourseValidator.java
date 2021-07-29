package pl.edu.wszib.learningplatform.cart;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.edu.wszib.learningplatform.enrolledcourse.EnrolledCourse;
import pl.edu.wszib.learningplatform.enrolledcourse.EnrolledCourseRepository;
import pl.edu.wszib.learningplatform.user.User;
import pl.edu.wszib.learningplatform.user.UserPrincipal;

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
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userPrincipal.getUser();
        EnrolledCourse userCourse = userCourseRepository.findByCourseIdAndUserId(courseId, user.getId());
        return !userCourse.isBought();
    }
}
