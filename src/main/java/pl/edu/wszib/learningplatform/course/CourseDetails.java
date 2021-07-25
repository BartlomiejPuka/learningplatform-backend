package pl.edu.wszib.learningplatform.course;

import lombok.Getter;
import pl.edu.wszib.learningplatform.course.scope.CourseScope;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Embeddable
@Getter
public class CourseDetails {
    private String author;
    @Lob
    private String description;

    private String iconUrl;

    private String urlSlug;

    @Column(length = 600)
    private String detailedDescription;

    @Column(length = 600)
    private String recipientDescription;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(inverseJoinColumns=@JoinColumn(name="scope_id"))
    private List<CourseScope> scopes = new ArrayList<>();

}
