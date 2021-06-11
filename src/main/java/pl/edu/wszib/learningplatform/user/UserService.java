package pl.edu.wszib.learningplatform.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {
    private final UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(Long userId) { return userRepository.findById(userId); }

    public Optional<User> findByUsername(String username) {return userRepository.findByUsername(username); }

    public User updateUser(User user) { return userRepository.save(user); }

}
