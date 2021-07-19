package pl.edu.wszib.learningplatform.cart;

import lombok.experimental.UtilityClass;
import pl.edu.wszib.learningplatform.course.Course;
import pl.edu.wszib.learningplatform.course.CourseDetails;

@UtilityClass
public class CartItemMapper {

    public CartItemDto toDto(CartItem cartItem) {
        Course course = cartItem.getUserCourse().getCourse();
        CourseDetails courseDetails = course.getDetails();
        return CartItemDto.builder()
                .id(cartItem.getId())
                .courseAuthor(courseDetails.getAuthor())
                .courserPrice(course.getPrice())
                .courseTitle(course.getTitle())
                .build();
    }
}
