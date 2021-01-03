package pl.edu.wszib.learningplatform.controllers.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class EnrollmentDto {
    private Long id;
    private Long userId;
    private Timestamp enrollmentDate;
    private Long courseId;
}
