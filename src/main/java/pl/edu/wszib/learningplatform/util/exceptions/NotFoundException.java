package pl.edu.wszib.learningplatform.util.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFoundException extends ResponseStatusException {
    private static final HttpStatus status = HttpStatus.NOT_FOUND;

    public NotFoundException() {
        super(status);
    }

    public NotFoundException(String reason) {
        super(status, reason);
    }
}
