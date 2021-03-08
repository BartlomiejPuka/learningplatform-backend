package pl.edu.wszib.learningplatform.course;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserCourseDto {
    private Long id;
    private String title;
    private String subTitle;
    private String description;
    private byte[] image;
    private boolean enrolled;

    public UserCourseDto(Long id, String title, String subTitle, String description, byte[] image, boolean enrolled) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.description = description;
        this.image = image;
        this.enrolled = enrolled;
    }
}
