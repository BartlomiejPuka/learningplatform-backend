package pl.edu.wszib.learningplatform.cart;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.edu.wszib.learningplatform.authentication.service.CustomUser;

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
        CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Cart cart = cartRepository.findByUserId(customUser.getId());
        if(cart == null) {
            return true;
        }
        List<CartItem> cartItems = cart.getCartItemList();
        return !cartItems.stream().filter(i->i.getCourse().getId() == courseId).findFirst().isPresent();
    }
}
