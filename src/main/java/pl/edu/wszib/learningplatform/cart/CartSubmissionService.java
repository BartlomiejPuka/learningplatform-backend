package pl.edu.wszib.learningplatform.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wszib.learningplatform.user.User;
import pl.edu.wszib.learningplatform.enrolledcourse.EnrolledCourse;
import pl.edu.wszib.learningplatform.enrolledcourse.EnrolledCourseCreationService;
import pl.edu.wszib.learningplatform.enrolledcourse.EnrolledCourseRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
public class CartSubmissionService {

    private final CartRepository cartRepository;
    private final EnrolledCourseCreationService userCourseCreationService;
    private final EnrolledCourseRepository userCourseRepository;

    public void submitCart(User user) {
        Cart cart = cartRepository.getByUserId(user.getId());
        if(cart == null || cart.getCartItemList().size() == 0) {
            throw new EmptyCartException("Cannot submit empty cart.");
        }
        List<EnrolledCourse> userCourseList = cart.getCartItemList().stream()
                .map(CartItem::getEnrolledCourse)
                .map(userCourseCreationService::setPurchasedInformation)
                .map(userCourseCreationService::initializeLessonsProgress)
                .map(userCourseCreationService::initializeTasksProgress)
                .collect(toList());
        userCourseRepository.saveAll(userCourseList);
        cartRepository.delete(cart);
    }
}
