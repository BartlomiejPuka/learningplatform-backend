package pl.edu.wszib.learningplatform.learningprogress.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table( name = "learning_progress")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LearningProgress {
    @Id
    public Long id;

    public Long enrollmentId;

    public Timestamp startDate;

    public Timestamp endDate;

    public boolean status;
}
