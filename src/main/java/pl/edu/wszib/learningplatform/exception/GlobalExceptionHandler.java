package pl.edu.wszib.learningplatform.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.edu.wszib.learningplatform.cart.EmptyCartException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiErrorResponse handleConstraintViolationException(ConstraintViolationException exception) {
        List<String> errors = exception.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(toList());
        return ApiErrorResponse.builder()
                .message("Constraints has been violated.")
                .errors(errors)
                .build();
    }

    @ExceptionHandler(EmptyCartException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiErrorResponse handleEmptyCartException(EmptyCartException exception) {

        return ApiErrorResponse.builder()
                .message("Api error occurred.")
                .errors(singletonList(exception.getMessage()))
                .build();
    }

}
