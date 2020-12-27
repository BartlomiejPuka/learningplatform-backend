package pl.edu.wszib.learningplatform.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wszib.learningplatform.user.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
