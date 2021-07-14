package pl.edu.wszib.learningplatform.cart;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.learningplatform.authentication.service.CustomUser;
import pl.edu.wszib.learningplatform.user.User;

import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CartItemDto> getAllCartItems(@AuthenticationPrincipal CustomUser customUser){
        return cartService.getAllCartItems(customUser.getId());
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public CartItemDto addCartItem(@RequestBody AddCartItemDto addCartItemDto, @AuthenticationPrincipal CustomUser customUser){
        return cartService.addCartItem(addCartItemDto, customUser.getId());
    }

    @PostMapping("/items/{id}/remove")
    @ResponseStatus(HttpStatus.OK)
    public boolean removeCartItem(@PathVariable("id") Long cartItemId, @AuthenticationPrincipal CustomUser customUser){
        return cartService.removeCartItem(cartItemId, customUser.getId());
    }
}
