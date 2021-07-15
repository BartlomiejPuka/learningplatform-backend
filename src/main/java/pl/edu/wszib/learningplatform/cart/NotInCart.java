package pl.edu.wszib.learningplatform.cart;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NotInCartValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotInCart {
    String message() default "Course with id = ${validatedValue} is already present in cart.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
