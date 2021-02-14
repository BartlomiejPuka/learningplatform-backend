package pl.edu.wszib.learningplatform.util.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ConflictException extends ResponseStatusException {
    private static final HttpStatus status = HttpStatus.CONFLICT;

    public ConflictException() {
        super(status);
    }

    public ConflictException(String reason) {
        super(status, reason);
    }
}
