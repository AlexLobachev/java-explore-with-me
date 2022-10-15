package ru.practicum.ewm.events;

import org.springframework.stereotype.Service;
import ru.practicum.ewm.events.model.Event;

import javax.validation.ValidationException;

import static ru.practicum.ewm.events.model.State.PUBLISHED;

@Service
public class EventValidator {
    public void checkUpdateState(Event event) {
        if (event.getState() == PUBLISHED) {
            throw new ValidationException("Изменить можно только отмененные события или события в состоянии ожидания модерации");
        }
    }

    public void checkRequestAdd(Event event) {
        if (event.getConfirmedRequests() >= event.getParticipantLimit()) {
            throw new ValidationException("Достигнут лимит заявок");
        }
    }

}

