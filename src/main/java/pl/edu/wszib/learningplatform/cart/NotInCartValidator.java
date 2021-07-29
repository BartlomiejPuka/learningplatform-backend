package pl.edu.wszib.learningplatform.cart;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.edu.wszib.learningplatform.user.User;
import pl.edu.wszib.learningplatform.user.UserPrincipal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

@Component
public class NotInCartValidator implements ConstraintValidator<NotInCart, Long> {

    private final CartRepository cartRepository;

    public NotInCartValidator(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public boolean isValid(Long courseId, ConstraintValidatorContext context) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userPrincipal.getUser();
        Cart cart = cartRepository.getByUserId(user.getId());
        if(cart == null) {
            return true;
        }
        List<CartItem> cartItems = cart.getCartItemList();
        return !cartItems.stream().filter(i->i.getEnrolledCourse().getCourse().getId() == courseId).findFirst().isPresent();
    }
}
