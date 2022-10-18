package ru.practicum.ewm.exceptions;

/**
 * Класс исключение NotFound
 *
 * @version 1.0
 * @autor Lobachev
 */
public class ExceptionNotFound extends RuntimeException {
    /**
     * Конструктор - создание сообщения
     *
     * @see ExceptionNotFound (String)
     */
    public ExceptionNotFound(String message) {
        super(message);
    }
}
