package pl.edu.wszib.learningplatform.progress.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table( name = "enrollments")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Progress {
    @Id
    public Long id;

    public Long enrollmentId;

    public Timestamp beginTimestamp;

    public Timestamp completionTimestamp;

    public boolean status;
}
