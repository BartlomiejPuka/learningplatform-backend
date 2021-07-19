package pl.edu.wszib.learningplatform.cart;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import pl.edu.wszib.learningplatform.course.Course;
import pl.edu.wszib.learningplatform.usercourse.UserCourse;

import javax.persistence.*;

@Entity
@Table(name = "cart_items")
@Setter
@Getter
public class CartItem {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private UserCourse userCourse;

    @ManyToOne(cascade = CascadeType.ALL)
    private Cart cart;

}
