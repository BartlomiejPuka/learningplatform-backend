package pl.edu.wszib.learningplatform.cart;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NotOwnedCourseValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotOwnedCourse {
    String message() default "Course with id = ${validatedValue} is already bought.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
