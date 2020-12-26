package pl.edu.wszib.learningplatform.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wszib.learningplatform.user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
