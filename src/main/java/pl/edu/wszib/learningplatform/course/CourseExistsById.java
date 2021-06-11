package pl.edu.wszib.learningplatform.course;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CourseExistsByIdValidator.class)
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseExistsById {
    String message() default "Course with id=${validatedValue} does not exists.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}