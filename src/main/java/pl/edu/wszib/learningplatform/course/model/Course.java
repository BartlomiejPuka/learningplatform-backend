package pl.edu.wszib.learningplatform.course.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edu.wszib.learningplatform.user.model.User;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table( name = "courses")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable
    private Set<User> users;
}
