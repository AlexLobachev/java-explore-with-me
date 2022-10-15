package ru.practicum.ewm.requests;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.events.model.Event;
import ru.practicum.ewm.events.model.State;
import ru.practicum.ewm.requests.model.Request;
import ru.practicum.ewm.requests.model.StatusRequest;

import javax.validation.ValidationException;

@Service
@RequiredArgsConstructor
public class RequestValidator {
    private final RequestRepository requestRepository;

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

    public void checkRequest(Event event, long eventId) {
        if (event.getState() == State.PENDING) {
            throw new ValidationException("Событие не опубликовано");
        }
        if (event.getConfirmedRequests().equals(event.getParticipantLimit())) {
            requestRepository.trowRequest(eventId, StatusRequest.CANCELED);
            throw new ValidationException("Достигнут лимит запросов на участие");
        }
    }
}
