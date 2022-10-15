package ru.practicum.ewm.events.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.OffsetLimitPageable;
import ru.practicum.ewm.categories.service.CategoryServiceImpl;
import ru.practicum.ewm.events.EventRepository;
import ru.practicum.ewm.events.EventValidator;
import ru.practicum.ewm.events.LocationRepository;
import ru.practicum.ewm.events.dto.EventDto;
import ru.practicum.ewm.events.dto.EventDtoFull;
import ru.practicum.ewm.events.model.Event;
import ru.practicum.ewm.exception.ExceptionNotFound;
import ru.practicum.ewm.exception.ExclusionInvalidRequest;
import ru.practicum.ewm.requests.dto.RequestDto;
import ru.practicum.ewm.requests.service.RequestServiceImpl;
import ru.practicum.ewm.users.service.UserServiceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static ru.practicum.ewm.events.dto.EventMapper.toDtoResponseEvent;
import static ru.practicum.ewm.events.dto.EventMapper.toEventDto;
import static ru.practicum.ewm.events.model.State.*;

@Service
@RequiredArgsConstructor
@Transactional
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;
    private final UserServiceImpl userService;
    private final CategoryServiceImpl categoryService;
    private final EventValidator eventValidator;
    private final RequestServiceImpl requestService;

    /**
     * PRIVATE  EVENT
     */
    public List<Event> getAllEventUser(long userId, Integer from, Integer size) {
        Pageable pageable = OffsetLimitPageable.of(from, size, Sort.by("id"));
        List<Event> events = eventRepository.findAllByInitiatorId(userId, pageable).stream().collect(Collectors.toList());
        if (events.size() == 0) {
            throw new ExceptionNotFound("Событий не найдено");
        }
        return events;
    }

    public EventDtoFull updateEvent(long userId, EventDto eventDto) {
        getEventById(eventDto.getEventId());
        Event event = eventRepository.findByInitiatorId(userId);
        eventValidator.checkUpdateState(event);
        //eventValidator.checkEventDate(toEventDto(eventDto));
        event.setCategory(categoryService.getCategoryById(eventDto.getCategory()));
        event.setState(PENDING);
        eventRepository.updateEvent(userId, eventDto.getAnnotation(), event.getCategory(), eventDto.getDescription(),
                eventDto.getEventDate(), eventDto.getEventId(), eventDto.getPaid(), eventDto.getParticipantLimit(), eventDto.getTitle(),
                event.getState(), eventDto.getRequestModeration());
        return toDtoResponseEvent(eventRepository.findById(event.getId()).orElse(new Event()));
    }

    public EventDtoFull editingAnEvent(long eventId, EventDto eventDto) {

        Event event = getEventById(eventDto.getEventId());

        event.setCategory(categoryService.getCategoryById(eventDto.getCategory()));
        event.setState(PENDING);

        eventRepository.updateEvent(event.getInitiator().getId(), eventDto.getAnnotation(), event.getCategory(), eventDto.getDescription(),
                eventDto.getEventDate(), eventDto.getEventId(), eventDto.getPaid(), eventDto.getParticipantLimit(), eventDto.getTitle(),
                event.getState(), eventDto.getRequestModeration());
        return toDtoResponseEvent(getEventById(eventId));
    }

    public Event createEvent(long userId, EventDto eventDto) {
        Event event = toEventDto(eventDto);
        //eventValidator.checkEventDate(event);
        event.setLocation(locationRepository.save(eventDto.getLocation()));
        event.setInitiator(userService.getUser(userId));
        event.setCategory(categoryService.getCategoryById(eventDto.getCategory()));
        event.setState(PENDING);
        return eventRepository.save(event);
    }

    public Event getEventUser(long userId, long eventId) {
        getEventById(eventId);
        return eventRepository.findByInitiatorIdAndId(userId, eventId);
    }

    public EventDtoFull cancelingUpdateEvent(long userId, long eventId) {
        getEventById(eventId);
        eventRepository.cancelingUpdateEvent(eventId, CANCELED);
        return toDtoResponseEvent(getEventById(eventId));
    }

    public List<RequestDto> getRequestsEventUser(long userId, long eventId) {
        return requestService.getRequestsEventUser(userId, eventId);
    }

    public RequestDto confirmTheApplication(long userId, long eventId, long reqId) {
        Event event = getEventById(eventId);
        eventValidator.checkRequestAdd(event);
        eventRepository.updateRequestsAdd(eventId);
        return requestService.confirmTheApplication(reqId);

    }

    public RequestDto rejectTheApplication(long userId, long eventId, long reqId) {
        getEventById(eventId);
        return requestService.rejectTheApplication(reqId);
    }

    /**
     * ADMIN  EVENT
     */
    public List<Event> getAllEventUser(List<Long> users, List<String> states,
                                       List<Integer> categories,
                                       LocalDateTime rangeStart, LocalDateTime rangeEnd,
                                       Integer from, Integer size) {
        Pageable pageable = OffsetLimitPageable.of(from, size, Sort.by("id"));
        List<Event> events = eventRepository.findAdmin(users, states,
                categories,
                rangeStart, rangeEnd,
                pageable).stream().collect(Collectors.toList());


        if (events.size() == 0) {
            throw new ExceptionNotFound("Событий не найдено");
        }
        return events;
    }

    public EventDtoFull rejectionEvent(long eventId) {
        eventRepository.eventState(eventId, CANCELED);
        return toDtoResponseEvent(getEventById(eventId));
    }

    public Event publishEvent(long eventId) {
        eventRepository.eventState(eventId, PUBLISHED);
        return eventRepository.findById(eventId).orElse(new Event());
    }

    /**
     * PUBLIC  EVENT
     */
    public List<Event> getAllEvent(String text, List<Long> categories,
                                   Boolean paid, LocalDateTime rangeStart,
                                   LocalDateTime rangeEnd,
                                   Boolean onlyAvailable, String sort,
                                   Integer from, Integer size) {
        switch (sort) {
            case "EVENT_DATE":
                sort = "eventDate";
                break;
            case "VIEWS":
                sort = "views";
                break;
            default:
                throw new ExclusionInvalidRequest("Ошибка правила сортировки");
        }
        Pageable pageable = OffsetLimitPageable.of(from, size, Sort.by(sort));
        eventRepository.updateViewAll();
        return eventRepository.findPublicDate(text, categories, rangeStart, rangeEnd, paid, pageable).stream().collect(Collectors.toList());
    }

    public Event getEventById(long id) {
        Event event = eventRepository.findById(id).orElse(new Event());
        if (event.getAnnotation() == null) {
            throw new ExceptionNotFound("Событие не найдено");
        }
        eventRepository.updateView(id);
        return eventRepository.findById(id).orElse(new Event());
    }


}
