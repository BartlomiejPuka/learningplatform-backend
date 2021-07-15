package pl.edu.wszib.learningplatform.cart;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import pl.edu.wszib.learningplatform.course.Course;
import pl.edu.wszib.learningplatform.course.CourseRepository;
import pl.edu.wszib.learningplatform.user.User;
import pl.edu.wszib.learningplatform.user.UserRepository;
import pl.edu.wszib.learningplatform.usercourse.UserCourse;
import pl.edu.wszib.learningplatform.usercourse.UserCourseRepository;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
@Validated
@Transactional
public class CartService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final CourseRepository courseRepository;
    private final UserCourseRepository userCourseRepository;

    public List<CartItemDto> getAllCartItems(Long userId) {
        User user = userRepository.getOne(userId);
        Cart cart = getUserCart(user);
        List<CartItem> cartItems = cart.getCartItemList();
        return cartItems.stream().map(CartItemMapper::toDto).collect(toList());
    }

    public CartItemDto addCartItem(@Valid AddCartItemDto addCartItemDto, Long userId) {
        User user = userRepository.getOne(userId);
        Course course = courseRepository.getOne(addCartItemDto.getCourseId());
        Cart cart = getUserCart(user);

        CartItem cartItem = new CartItem();
        cartItem.setCourse(course);
        cartItem = cartItemRepository.save(cartItem);

        cart.addCartItem(cartItem);
        cartRepository.save(cart);

        return CartItemMapper.toDto(cartItem);
    }

    private Cart getUserCart(User user){
        Cart cart = cartRepository.findByUserId(user.getId());
        if(cart == null){
            cart = createUserCart(user);
        }
        return cart;
    }

    private Cart createUserCart(User user){
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setCartItemList(new ArrayList<>());
        return cartRepository.save(cart);
    }

    public boolean removeCartItem(Long cartItemId, Long userId) {
        CartItem cartItem = cartItemRepository.getOne(cartItemId);
        Cart cart = cartRepository.findByUserId(userId);
        cart.removeCartItem(cartItem);
        cartRepository.save(cart);
        cartItemRepository.delete(cartItem);
        return true;
    }

    public boolean submitCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);
        createUserCourses(cart);
        cartRepository.delete(cart);
        return true;
    }

    private void createUserCourses(Cart cart) {
        List<CartItem> cartItems = cart.getCartItemList();
        cartItems.stream()
                .map(this::createUserCourse)
                .forEach(userCourseRepository::save);
    }

    private UserCourse createUserCourse(CartItem cartItem){
        UserCourse userCourse = new UserCourse();
        Course course = cartItem.getCourse();
        userCourse.setCourse(course);
        return userCourse;
    }
}
