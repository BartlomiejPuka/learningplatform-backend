package pl.edu.wszib.learningplatform.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wszib.learningplatform.user.User;

@RequiredArgsConstructor
@Service
public class CartOrderService {

    private final CartRepository cartRepository;
    private final CartCourseCreationService cartCourseCreationService;
    // TODO: validate if cart is not empty otherwise throw exception.
    public void submitCart(User user) {
        Cart cart = cartRepository.getByUserId(user.getId());
        cartCourseCreationService.createUserCourses(cart);
        cartRepository.delete(cart);
    }
}
