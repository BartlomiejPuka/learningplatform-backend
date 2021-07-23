package pl.edu.wszib.learningplatform.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUserId(Long userId);

    Cart getByUserId(Long userId);

    @Query("SELECT c.cartItemList.size FROM Cart c " +
            "WHERE c.user.id = :userId ")
    Optional<Long> countCartItemListByUserId(Long userId);

}
