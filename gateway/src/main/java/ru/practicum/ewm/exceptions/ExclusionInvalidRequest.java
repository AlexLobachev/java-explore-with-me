package ru.practicum.ewm.exceptions;

/**
 * Класс исключение InvalidReques
 *
 * @version 1.0
 * @autor Lobachev
 */
public class ExclusionInvalidRequest extends RuntimeException {
    /**
     * Конструктор - создание сообщения
     *
     * @see ExclusionInvalidRequest (String)
     */
    public ExclusionInvalidRequest(String message) {
        super(message);
    }
}