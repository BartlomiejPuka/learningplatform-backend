package pl.edu.wszib.learningplatform.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.edu.wszib.learningplatform.util.exceptions.BadRequestException;
import pl.edu.wszib.learningplatform.util.exceptions.NotFoundException;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static pl.edu.wszib.learningplatform.util.message.MessageTemplates.USER_NOT_FOUND_MESSAGE_TEMPLATE;

@Component
@RequiredArgsConstructor
public class UserComponent {
    private final UserService userService;
    private final UserMapper userAssembler;
    private final PasswordEncoder passwordEncoder;


    public List<UserDto> getUsers() {
        List<User> userModels = userService.findAll();
        return userModels.stream().map(userAssembler::toDto).collect(toList());
    }

    public UserDto updateUserEmail(Long userId, EmailUpdateDto email) {
        User user = userService.findById(userId).orElseThrow(
                () -> new NotFoundException(String.format(USER_NOT_FOUND_MESSAGE_TEMPLATE, userId)));

        updateUserEmail(user, email.getEmail());

        return userAssembler.toDto(userService.updateUser(user));
    }

    public UserDto updateUserPassword(Long userId, PasswordUpdateDto passwordUpdateDto) {
        User user = userService.findById(userId).orElseThrow(
                () -> new NotFoundException(String.format(USER_NOT_FOUND_MESSAGE_TEMPLATE, userId)));

        updateUserPassword(user, passwordUpdateDto);

        return userAssembler.toDto(userService.updateUser(user));
    }

    private void updateUserPassword(User user, PasswordUpdateDto passwordUpdateDto){
        if(passwordUpdateDto.getNewPassword() != null){
            checkActualUserPassword(passwordUpdateDto.getCurrentPassword(), user.getPassword());
            user.setPassword(passwordEncoder.encode(passwordUpdateDto.getNewPassword()));
        }
    }

    private void checkActualUserPassword(String currentPassword, String passwordHash) {
        if (!passwordEncoder.matches(currentPassword, passwordHash)) {
            throw new BadRequestException("Invalid password.");
        }
    }

    private void updateUserEmail(User user, String email) {
        if (email != null) {
            user.setEmail(email);
        }
    }
}
