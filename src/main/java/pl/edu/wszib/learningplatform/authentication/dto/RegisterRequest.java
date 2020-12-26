package pl.edu.wszib.learningplatform.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.wszib.learningplatform.user.model.User;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;
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
