package pl.edu.wszib.learningplatform.controllers;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class EnrolledCourseDto {
    private Long id;
    private String courseTitle;
}
