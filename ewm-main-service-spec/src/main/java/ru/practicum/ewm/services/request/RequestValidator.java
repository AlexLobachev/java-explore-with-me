package ru.practicum.ewm.services.request;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.models.event.State;
import ru.practicum.ewm.models.request.Request;
import ru.practicum.ewm.repositories.request.RequestRepository;

import javax.validation.ValidationException;

/**
 * Класс валидации запросов.
 *
 * @version 1.0
 * @autor Lobachev
 */
@Service
@RequiredArgsConstructor
public class RequestValidator {

    /**
     * Метод - валидация событий
     *
     * @param userId  - id текущего пользователя
     * @param request - запрос
     */
    public void checkCreateRequest(Request request, long userId) {
        if (request.getEvent().getInitiator().getId() == userId) {
            throw new ValidationException("Инициатор события не может добавить запрос на участие в своём событии");
        }
        if (request.getEvent().getState() == State.PENDING) {
            throw new ValidationException("Событие не опубликовано");
        }
        if (request.getEvent().getConfirmedRequests() > (request.getEvent().getParticipantLimit())) {
            throw new ValidationException("Достигнут лимит запросов на участие");
        }

    }

}
