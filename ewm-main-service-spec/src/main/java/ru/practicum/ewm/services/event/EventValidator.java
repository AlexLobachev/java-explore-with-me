package ru.practicum.ewm.services.event;

import org.springframework.stereotype.Service;
import ru.practicum.ewm.models.event.Event;
import ru.practicum.ewm.models.event.State;

import javax.validation.ValidationException;


/**
 * Класс валидации событий.
 *
 * @version 1.0
 * @autor Lobachev
 */
@Service
public class EventValidator {
    /**
     * Метод - Валидация на изменение событий
     *
     * @param event - текст для поиска в содержимом аннотации и подробном описании события
     */
    public void checkUpdateState(Event event) {
        if (event.getState() == State.PUBLISHED) {
            throw new ValidationException("Изменить можно только отмененные события или события в состоянии ожидания модерации");
        }
    }

    /**
     * Метод - Валидация на изменение событий
     *
     * @param event - валидация на лимит заявок
     */
    public void checkRequestAdd(Event event) {
        if (event.getConfirmedRequests() >= event.getParticipantLimit()) {
            throw new ValidationException("Достигнут лимит заявок");
        }
    }

}

