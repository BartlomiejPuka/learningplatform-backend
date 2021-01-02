package pl.edu.wszib.learningplatform.subcourse.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edu.wszib.learningplatform.lesson.model.Lesson;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SubCourse {

    @javax.persistence.Id
    public Long id;

    @OneToMany()
    private Set<Lesson> lessons;
}
