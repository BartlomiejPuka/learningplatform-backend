package pl.edu.wszib.learningplatform.cart

import org.springframework.beans.factory.annotation.Autowire
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.test.context.support.WithUserDetails
import org.springframework.test.context.ActiveProfiles
import pl.edu.wszib.learningplatform.BaseIT
import pl.edu.wszib.learningplatform.course.Course
import pl.edu.wszib.learningplatform.course.CourseRepository
import pl.edu.wszib.learningplatform.exception.ApiErrorResponse
import pl.edu.wszib.learningplatform.user.User
import pl.edu.wszib.learningplatform.user.UserRepository

import static org.springframework.http.HttpStatus.*

@WithUserDetails("testUser")
@ActiveProfiles("test")
class CartControllerSpec extends BaseIT {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartItemRepository cartItemRepository

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    UserRepository userRepository;

    def "should be able to add item to cart" () {
        setup:
            Long courseId = 3
            AddCartItemDto addCartItemDto = new AddCartItemDto()
            addCartItemDto.setCourseId(courseId)
        when:
            def response = performPost("/api/cart/add", addCartItemDto)
        then:
            verifyAll {
                response.status == CREATED.value()
            }
    }

    def "should not be able to add item that is already in cart" () {
        setup:
            Long courseId = 3
            setupCart(courseId)

            AddCartItemDto addCartItemDto = new AddCartItemDto()
            addCartItemDto.setCourseId(courseId)
        when:
            def response = performPost("/api/cart/add", addCartItemDto)
        then:
            verifyAll {
                ApiErrorResponse apiErrorResponse = objectMapper.readValue(response.getContentAsString(), ApiErrorResponse.class)
                apiErrorResponse.getErrors() == ["Course with id = ${ courseId } is already present in cart."]
                apiErrorResponse.getMessage() == "Constraints has been violated."
                response.status == BAD_REQUEST.value()
            }
    }

    def "should be able to get list of cart items" () {
        when:
            def response = performGet("/api/courses", null)
        then:
            verifyAll {
                response.status == OK.value()
            }
    }

    def "should be able to remove item from cart" () {
        setup:
            Long courseId = 3
            Cart cart = setupCart(courseId)
            CartItem cartItem = cart.getCartItemList().first()
            Long cartItemId = cartItem.getId()
        when:
            def response = performPost("/api/cart/items/${cartItemId}/remove", null)
        then:
            verifyAll {
                response.status == OK.value()
            }
    }

    def "should not be able to remove item that does not exists in cart" () {
        setup:
            Long cartItemId = -99
        when:
            def response = performPost("/api/cart/items/${cartItemId}/remove", null)
        then:
            verifyAll {
                ApiErrorResponse apiErrorResponse = objectMapper.readValue(response.getContentAsString(), ApiErrorResponse.class)
                apiErrorResponse.getErrors() == ["CartItem with id = ${ cartItemId } does not exists."]
                apiErrorResponse.getMessage() == "Constraints has been violated."
                response.status == BAD_REQUEST.value()
            }
    }

    def "should be able to submit cart" () {
        setup:
            Long courseId = 3
            setupCart(courseId)
        when:
            def response = performPost("/api/cart/submit", null)
        then:
            verifyAll {
                response.status == OK.value()
            }
    }

    def "should not be able to submit empty cart" () {
        setup:
            Long courseId = 3
            Cart cart = setupCart(courseId)
            cart.getCartItemList().forEach({
                it ->
                    cartItemRepository.deleteById(it.getId())
            })
            cart.setCartItemList(new ArrayList<CartItem>())
            cartRepository.save(cart)
        when:
           def response = performPost("/api/cart/submit", null)
        then:
            verifyAll {
                ApiErrorResponse apiErrorResponse = objectMapper.readValue(response.getContentAsString(), ApiErrorResponse.class)
                apiErrorResponse.getErrors() == ["Cannot submit empty cart."]
                apiErrorResponse.getMessage() == "Api error occurred."
                response.status == BAD_REQUEST.value()
            }
    }

    def cleanup() {
        cartRepository.deleteAll()
    }

    def setupCart(courseId) {
        Course course = courseRepository.findById(courseId).get()
        User user = userRepository.findByUsername("testUser").get()
        Cart cart = new Cart()
        cart.setUser(user)
        CartItem cartItem = new CartItem()
        cartItem.setCourse(course)
        cart.addCartItem(cartItem)
        return cartRepository.save(cart)
    }

}
