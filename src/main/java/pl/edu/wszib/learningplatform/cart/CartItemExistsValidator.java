package pl.edu.wszib.learningplatform.cart;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class CartItemExistsValidator implements ConstraintValidator<CartItemExists, Long> {

    private final CartItemRepository cartItemRepository;

    public CartItemExistsValidator(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public boolean isValid(Long cartItemId, ConstraintValidatorContext context) {
        return cartItemRepository.existsById(cartItemId);
    }
}
