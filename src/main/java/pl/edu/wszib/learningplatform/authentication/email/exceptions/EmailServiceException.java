package pl.edu.wszib.learningplatform.authentication.email.exceptions;

public class EmailServiceException extends RuntimeException {
    public EmailServiceException(String message, Exception e) {
        super(message, e);
    }
}
