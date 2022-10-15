package ru.practicum.ewm.requests.service;

import ru.practicum.ewm.requests.dto.RequestDto;
import ru.practicum.ewm.requests.model.Request;

import java.util.List;

public interface RequestService {
    List<Request> getRequestUserAlienEvent(long userId);

    Request createRequest(long userId, long eventId);

    RequestDto cancelRequest(long userId, long requestId);


}
