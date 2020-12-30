package pl.edu.wszib.learningplatform.controllers.dto;

import lombok.Getter;
import lombok.Setter;
import pl.edu.wszib.learningplatform.util.annotations.ValidPassword;

@Getter
@Setter
public class PasswordUpdateDto {
    public String currentPassword;
    @ValidPassword
    public String newPassword;
}
