package pl.edu.wszib.learningplatform.exception;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@Builder
@RequiredArgsConstructor
public class ApiErrorResponse {
    private final List<String> errors;
    private final String message;
}
