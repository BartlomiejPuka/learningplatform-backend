package pl.edu.wszib.learningplatform.util.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BadRequestException extends ResponseStatusException {
    private static final HttpStatus status = HttpStatus.BAD_REQUEST;

    public BadRequestException() {
        super(status);
    }

    public BadRequestException(String reason) {
        super(status, reason);
    }
}
