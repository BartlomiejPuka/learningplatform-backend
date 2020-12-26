package pl.edu.wszib.learningplatform.authentication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.wszib.learningplatform.user.model.User;

import javax.persistence.*;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "token")
public class VerificationToken {

    @Id
    @GeneratedValue
    private Long id;
    private String token;
    @OneToOne(fetch = FetchType.LAZY)
    private User user;
    private Instant expiryDate;
}
