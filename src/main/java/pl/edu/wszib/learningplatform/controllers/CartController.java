package pl.edu.wszib.learningplatform.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.learningplatform.authentication.service.CustomUser;
import pl.edu.wszib.learningplatform.cart.AddCartItemDto;
import pl.edu.wszib.learningplatform.cart.CartItemDto;
import pl.edu.wszib.learningplatform.cart.CartManagementService;
import pl.edu.wszib.learningplatform.cart.CartOrderService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cart")
@Tag(name = "Cart", description = "All endpoints that manage cart operations.")
public class CartController {

    private final CartManagementService cartManagementService;
    private final CartOrderService cartOrderService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all items in cart for logged in user.", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = CartItemDto.class)))
            })
    })
    public List<CartItemDto> getAllCartItems(@AuthenticationPrincipal CustomUser customUser){
        return cartManagementService.getAllCartItems(customUser.getUser());
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add item to user cart.", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = CartItemDto.class))
            })
    })
    public CartItemDto addCartItem(@RequestBody AddCartItemDto addCartItemDto, @AuthenticationPrincipal CustomUser customUser){
        return cartManagementService.addCartItem(addCartItemDto, customUser.getUser());
    }

    @PostMapping("/items/{id}/remove")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Remove item from user cart.", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200")
    })
    public void removeCartItem(@PathVariable("id") Long cartItemId){
        cartManagementService.removeCartItem(cartItemId);
    }

    @PostMapping("/submit")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buying cart's items.", responses = {
            @ApiResponse(description = "Succesful Operation", responseCode = "200")
    })
    public void submitCart(@AuthenticationPrincipal CustomUser customUser){
        cartOrderService.submitCart(customUser.getUser());
    }
}
