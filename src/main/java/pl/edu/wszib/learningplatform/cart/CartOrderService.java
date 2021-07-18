package pl.edu.wszib.learningplatform.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wszib.learningplatform.user.User;

@RequiredArgsConstructor
@Service
public class CartOrderService {

    private final CartRepository cartRepository;
    private final CartCourseCreationService cartCourseCreationService;

    public void submitCart(User user) {
        Cart cart = cartRepository.getByUserId(user.getId());
        if(cart == null || cart.getCartItemList().size() == 0) {
            throw new EmptyCartException("Cannot submit empty cart.");
        }
        cartCourseCreationService.createUserCourses(cart);
        cartRepository.delete(cart);
    }
}
