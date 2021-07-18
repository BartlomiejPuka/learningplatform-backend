package pl.edu.wszib.learningplatform.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddCartItemDto {
    @NotOwnedCourse
    @NotInCart
    Long courseId;
}
