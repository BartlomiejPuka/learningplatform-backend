package pl.edu.wszib.learningplatform.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edu.wszib.learningplatform.course.model.Course;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.util.Set;

@Entity
@Table( name = "users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    // TODO: idea, change Long to UUID.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotBlank(message = "username is required")
    private String username;

    @NotBlank(message = "password is required")
    private String password;

    @Email
    @NotEmpty(message = "email is required")
    private String email;

    private boolean enabled;
    private Instant createdAt;

    @ManyToMany(mappedBy = "users")
    private Set<Course> courses;
}
