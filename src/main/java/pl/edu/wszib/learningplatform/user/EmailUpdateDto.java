package pl.edu.wszib.learningplatform.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
public class EmailUpdateDto {
    @Email
    public String email;
}
