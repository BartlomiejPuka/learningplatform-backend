package pl.edu.wszib.learningplatform.exception;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiErrorResponse {
    private List<String> errors;
    private String message;
}
