package pl.edu.wszib.learningplatform.cart;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CartItemExistsValidator.class)
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CartItemExists {
    String message() default "CartItem with id = ${validatedValue} does not exists.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

