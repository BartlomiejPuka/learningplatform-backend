package pl.edu.wszib.learningplatform.enrollment.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table( name = "enrollments")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Enrollment {

    @Id
    public Long id;

    public Long userId;

    public Long subCourseId;

    public LocalDateTime enrollmentDate;
}
