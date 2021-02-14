package pl.edu.wszib.learningplatform.util.exceptions;

public class EmailServiceException extends RuntimeException {
    public EmailServiceException(String message, Exception e) {
        super(message, e);
    }
}
