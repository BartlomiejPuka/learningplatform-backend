package pl.edu.wszib.learningplatform.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wszib.learningplatform.authentication.model.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    void deleteByToken(String token);
}