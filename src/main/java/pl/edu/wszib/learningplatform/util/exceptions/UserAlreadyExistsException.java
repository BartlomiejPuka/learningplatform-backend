package pl.edu.wszib.learningplatform.util.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyExistsException extends ResponseStatusException {
    private static final HttpStatus status = HttpStatus.CONFLICT;

    public UserAlreadyExistsException() {
        super(status);
    }

    public UserAlreadyExistsException(String reason) {
            super(status, reason);
        }
}

