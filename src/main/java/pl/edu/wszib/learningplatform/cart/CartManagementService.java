package pl.edu.wszib.learningplatform.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import pl.edu.wszib.learningplatform.course.Course;
import pl.edu.wszib.learningplatform.course.CourseRepository;
import pl.edu.wszib.learningplatform.user.User;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
@Validated
public class CartManagementService {

    private final CourseRepository courseRepository;
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;

    public List<CartItemDto> getAllCartItems(User user) {
        Cart cart = getUserCart(user);
        List<CartItem> cartItems = cart.getCartItemList();
        return cartItems.stream().map(CartItemMapper::toDto).collect(toList());
    }

    public CartItemDto addCartItem(@Valid AddCartItemDto addCartItemDto, User user) {
        Course course = courseRepository.getOne(addCartItemDto.getCourseId());
        Cart cart = getUserCart(user);

        CartItem cartItem = new CartItem();
        cartItem.setCourse(course);
        cartItem = cartItemRepository.save(cartItem);

        cart.addCartItem(cartItem);
        cartRepository.save(cart);

        return CartItemMapper.toDto(cartItem);
    }

    public void removeCartItem(@CartItemExists Long cartItemId) {
        CartItem cartItem = cartItemRepository.getOne(cartItemId);
        Cart cart = cartItem.getCart();
        cart.removeCartItem(cartItem);
        cartRepository.save(cart);
        cartItemRepository.delete(cartItem);
    }

    private Cart getUserCart(User user) {
        return cartRepository.findByUserId(user.getId()).orElseGet(() -> createUserCart(user));
    }

    private Cart createUserCart(User user){
        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

}
