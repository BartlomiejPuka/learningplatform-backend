package pl.edu.wszib.learningplatform.course;

import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.Lob;

@Embeddable
@Getter
public class CourseDetails {
    private String author;
    @Lob
    private String description;

    private String iconUrl;
}
