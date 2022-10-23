package ru.practicum.ewm.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ValidationException;
import java.util.Map;

/**
 * Класс обработки исключений
 *
 * @version 1.0
 * @autor Lobachev
 */
@RestControllerAdvice("ru.practicum.ewm")
public class ExceptionController {
    /**
     * Метод - вернуть BAD_REQUEST (status code = 400)
     *
     * @param e - RuntimeException
     */
    @ExceptionHandler({ValidationException.class, MethodArgumentTypeMismatchException.class, ExclusionInvalidRequest.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> errorException(final RuntimeException e) {

        return Map.of("error", e.getMessage());
    }

    /**
     * Метод - вернуть NOT_FOUND (status code = 404)
     *
     * @param e - ExceptionNotFound
     */
    @ExceptionHandler({ExceptionNotFound.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> errorException(final ExceptionNotFound e) {
        return Map.of("error", e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> errorException(final NullPointerException e) {
        return Map.of("Message", e.getMessage());
    }


}
