package ru.practicum.ewm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ValidationException;
import java.util.Map;

@RestControllerAdvice("ru.practicum.ewm")
public class ExceptionController {
    @ExceptionHandler({ValidationException.class, MethodArgumentTypeMismatchException.class, ExclusionInvalidRequest.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> errorException(final RuntimeException e) {

        return Map.of("error", e.getMessage());
    }

    @ExceptionHandler({ExceptionNotFound.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> errorException(final ExceptionNotFound e) {
        return Map.of("error", e.getMessage());
    }


}
