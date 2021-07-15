package pl.edu.wszib.learningplatform.authentication.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUser extends User {

    private Long id;

    private pl.edu.wszib.learningplatform.user.User user;

    public Long getId() {
        return id;
    }

    public pl.edu.wszib.learningplatform.user.User getUser() {
        return user;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public CustomUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired,
                      boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, Long id,
                      pl.edu.wszib.learningplatform.user.User user) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
        this.user = user;
    }
}
