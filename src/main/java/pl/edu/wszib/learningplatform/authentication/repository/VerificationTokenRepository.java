package pl.edu.wszib.learningplatform.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wszib.learningplatform.authentication.model.VerificationToken;

import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByToken(String token);
}
