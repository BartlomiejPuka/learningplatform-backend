package pl.edu.wszib.learningplatform.cart;


import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class CartItemDto {
    Long id;
    String courseTitle;
    BigDecimal courserPrice;
    String courseAuthor;
}
