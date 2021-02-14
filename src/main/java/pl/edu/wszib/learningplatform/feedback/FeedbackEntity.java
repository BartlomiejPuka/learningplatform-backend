package pl.edu.wszib.learningplatform.feedback;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table( name = "feedbacks")
@Setter
@Getter
public class FeedbackEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Lob
    public String text;

    public Integer ratingScore;

    @CreatedDate
    public LocalDateTime submissionDate;

    public Long enrollmentId;

}
