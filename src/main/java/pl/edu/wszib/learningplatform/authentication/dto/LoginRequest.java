package pl.edu.wszib.learningplatform.authentication.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotEmpty(message = "Username should not be empty.")
    private String username;
    @NotEmpty(message = "Password should not be empty.")
    private String password;
}
