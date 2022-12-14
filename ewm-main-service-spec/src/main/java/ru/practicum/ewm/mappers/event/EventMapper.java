package ru.practicum.ewm.mappers.event;


import ru.practicum.ewm.dtos.event.EventDto;
import ru.practicum.ewm.dtos.event.EventDtoFull;
import ru.practicum.ewm.dtos.event.EventShortDto;
import ru.practicum.ewm.dtos.event.UpdateEventRequest;
import ru.practicum.ewm.models.event.Event;

import static ru.practicum.ewm.mappers.location.LocationMapper.toDtoLocation;
import static ru.practicum.ewm.mappers.user.UserDtoMapper.userShortDto;

/**
 * Класс маппер ДТО событий
 *
 * @version 1.0
 * @autor Lobachev
 */
public class EventMapper {
    /**
     * Метод - конвертировать EventDto -> Event
     *
     * @param eventDto - объект EventDto
     */
    public static Event toEventDto(EventDto eventDto) {
        Event event = new Event();
        event.setAnnotation(eventDto.getAnnotation());
        event.setDescription(eventDto.getDescription());
        event.setEventDate(eventDto.getEventDate());
        event.setPaid(eventDto.getPaid());
        event.setParticipantLimit(eventDto.getParticipantLimit());
        event.setRequestModeration(eventDto.getRequestModeration());
        event.setTitle(eventDto.getTitle());
        return event;

    }

    /**
     * Метод - конвертировать Event -> EventDtoFull
     *
     * @param event - объект Event
     */
    public static EventDtoFull toDtoResponseEvent(Event event) {
        EventDtoFull eventDtoResponse = new EventDtoFull();
        eventDtoResponse.setAnnotation(event.getAnnotation());
        eventDtoResponse.setCategory(event.getCategory());
        eventDtoResponse.setDescription(event.getDescription());
        eventDtoResponse.setId(event.getId());
        eventDtoResponse.setEventDate(event.getEventDate());
        eventDtoResponse.setInitiator(userShortDto(event.getInitiator()));
        eventDtoResponse.setLocation(toDtoLocation(event.getLocation()));
        eventDtoResponse.setPaid(event.getPaid());
        eventDtoResponse.setParticipantLimit(event.getParticipantLimit());
        eventDtoResponse.setRequestModeration(event.getRequestModeration());
        eventDtoResponse.setTitle(event.getTitle());
        eventDtoResponse.setState(event.getState());
        eventDtoResponse.setConfirmedRequests(event.getConfirmedRequests());
        eventDtoResponse.setCreatedOn(event.getCreatedOn());
        eventDtoResponse.setPublishedOn(event.getPublishedOn());
        event.setViews(event.getViews());
        return eventDtoResponse;


    }

    /**
     * Метод - конвертировать Event -> EventShortDto
     *
     * @param event - объект Event
     */
    public static EventShortDto eventShortDto(Event event) {
        EventShortDto eventShortDto = new EventShortDto();
        eventShortDto.setAnnotation(event.getAnnotation());
        eventShortDto.setCategory(event.getCategory());
        eventShortDto.setConfirmedRequests(event.getConfirmedRequests());
        eventShortDto.setEventDate(event.getEventDate());
        eventShortDto.setId(event.getId());
        eventShortDto.setInitiator(userShortDto(event.getInitiator()));
        eventShortDto.setPaid(event.getPaid());
        eventShortDto.setTitle(event.getTitle());
        eventShortDto.setViews(event.getViews());

        return eventShortDto;

    }

    /**
     * Метод - конвертировать Event -> UpdateEventRequest
     *
     * @param event - объект Event
     */
    public static UpdateEventRequest updateEventRequest(Event event) {
        UpdateEventRequest updateEventRequest = new UpdateEventRequest();
        updateEventRequest.setAnnotation(event.getAnnotation());
        updateEventRequest.setCategory(event.getCategory());
        updateEventRequest.setDescription(event.getDescription());
        updateEventRequest.setEventDate(event.getEventDate());
        updateEventRequest.setId(event.getId());
        updateEventRequest.setPaid(event.getPaid());
        updateEventRequest.setTitle(event.getTitle());
        return updateEventRequest;

    }


}
