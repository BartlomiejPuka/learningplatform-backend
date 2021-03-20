package pl.edu.wszib.learningplatform.course.module;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "modules")
@Getter
@Setter
public class ModuleEntity {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    private String title;

    @Lob
    private String description;

    @Lob
    private byte[] image;

    private Long courseId;

}
