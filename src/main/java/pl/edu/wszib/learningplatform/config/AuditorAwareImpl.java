package pl.edu.wszib.learningplatform.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.edu.wszib.learningplatform.authentication.service.CustomUser;
import pl.edu.wszib.learningplatform.user.User;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<User> {

    @Override
    public Optional<User> getCurrentAuditor() {
        CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Optional.ofNullable(customUser.getUser());
    }

}
