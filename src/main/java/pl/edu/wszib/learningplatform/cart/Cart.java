package pl.edu.wszib.learningplatform.cart;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import pl.edu.wszib.learningplatform.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
@Setter
@Getter
public class Cart {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @OneToMany
    private List<CartItem> cartItemList = new ArrayList<>();

    public void addCartItem(CartItem cartItem) {
        cartItemList.add(cartItem);
    }

    public void removeCartItem(CartItem cartItem){
        cartItemList.remove(cartItem);
    }
}
