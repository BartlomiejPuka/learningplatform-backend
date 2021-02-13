package pl.edu.wszib.learningplatform.user;

import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.learningplatform.base.DtoMapper;

@Service
public class UserMapper extends DtoMapper<User, UserDto> {

    private final ModelMapper mapper;

    @Autowired
    public UserMapper(){
        mapper = new ModelMapper();

        mapper.typeMap(User.class, UserDto.class).addMappings(m -> {
        });

        mapper.typeMap(UserDto.class, User.class).addMappings(m -> {
            m.skip(User::setId);
        });
    }

    @Override
    public User toEntity(UserDto userDto) { return mapper.map(userDto, User.class); }

    @NotNull
    @Override
    public UserDto toDto(User user) { return mapper.map(user, UserDto.class); }
}
