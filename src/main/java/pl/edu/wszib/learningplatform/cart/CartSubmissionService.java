package pl.edu.wszib.learningplatform.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wszib.learningplatform.user.User;
import pl.edu.wszib.learningplatform.usercourse.UserCourse;
import pl.edu.wszib.learningplatform.usercourse.UserCourseCreationService;
import pl.edu.wszib.learningplatform.usercourse.UserCourseRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
public class CartSubmissionService {

    private final CartRepository cartRepository;
    private final UserCourseCreationService userCourseCreationService;
    private final UserCourseRepository userCourseRepository;

    public void submitCart(User user) {
        Cart cart = cartRepository.getByUserId(user.getId());
        if(cart == null || cart.getCartItemList().size() == 0) {
            throw new EmptyCartException("Cannot submit empty cart.");
        }
        List<UserCourse> userCourseList = cart.getCartItemList().stream()
                .map(CartItem::getUserCourse)
                .map(userCourseCreationService::setPurchasedInformation)
                .map(userCourseCreationService::initializeLessonsProgress)
                .map(userCourseCreationService::initializeTasksProgress)
                .collect(toList());
        userCourseRepository.saveAll(userCourseList);
        cartRepository.delete(cart);
    }
}
