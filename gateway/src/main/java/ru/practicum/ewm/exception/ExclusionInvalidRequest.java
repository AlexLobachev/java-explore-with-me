package ru.practicum.ewm.exception;

public class ExclusionInvalidRequest extends RuntimeException {
    public ExclusionInvalidRequest(String message) {
        super(message);
    }
}