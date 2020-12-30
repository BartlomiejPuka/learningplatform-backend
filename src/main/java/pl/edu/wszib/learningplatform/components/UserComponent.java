package pl.edu.wszib.learningplatform.components;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.edu.wszib.learningplatform.controllers.dto.UserDto;
import pl.edu.wszib.learningplatform.user.model.User;
import pl.edu.wszib.learningplatform.user.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserComponent {
    private final UserService userService;

    public List<UserDto> getUsers() {
        List<User> userModels = userService.findAll();
        return userModels.stream().map(i->i.toDto()).collect(Collectors.toList());
    }
}
