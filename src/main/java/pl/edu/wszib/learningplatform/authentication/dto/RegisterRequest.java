package pl.edu.wszib.learningplatform.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.wszib.learningplatform.user.model.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotEmpty(message = "Username should not be empty.")
    private String username;
    @NotEmpty(message = "Password should not be empty.")
    private String password;
    @Email(message = "You have to provide valid email address.")
    private String email;

    public User toUser(){
        User user = new User();
        user.setUsername(this.getUsername());
        user.setEmail(this.getEmail());
        user.setCreatedAt(Instant.now());
        user.setEnabled(false);
        return user;
    }
}
