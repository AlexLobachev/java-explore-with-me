package ru.practicum.ewm.events.service;

import ru.practicum.ewm.events.dto.EventDto;
import ru.practicum.ewm.events.dto.EventDtoFull;
import ru.practicum.ewm.events.model.Event;
import ru.practicum.ewm.requests.dto.RequestDto;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService {
    List<Event> getAllEventUser(long userId, Integer from, Integer size);

    EventDtoFull updateEvent(long userId, EventDto eventDto);

    EventDtoFull editingAnEvent(long eventId, EventDto eventDto);

    Event createEvent(long userId, EventDto eventDto);

    Event getEventUser(long userId, long eventId);

    EventDtoFull cancelingUpdateEvent(long userId, long eventId);

    List<RequestDto> getRequestsEventUser(long userId, long eventId);

    RequestDto confirmTheApplication(long userId, long eventId, long reqId);

    RequestDto rejectTheApplication(long userId, long eventId, long reqId);

    List<Event> getAllEventUser(List<Long> users, List<String> states,
                                List<Integer> categories,
                                LocalDateTime rangeStart, LocalDateTime rangeEnd,
                                Integer from, Integer size);

    EventDtoFull rejectionEvent(long eventId);

    Event publishEvent(long eventId);

    List<Event> getAllEvent(String text, List<Long> categories,
                            Boolean paid, LocalDateTime rangeStart,
                            LocalDateTime rangeEnd,
                            Boolean onlyAvailable, String sort,
                            Integer from, Integer size);

    Event getEventById(long id);
}
