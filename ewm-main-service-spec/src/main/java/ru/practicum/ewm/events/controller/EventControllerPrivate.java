package ru.practicum.ewm.events.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.events.dto.EventDto;
import ru.practicum.ewm.events.dto.EventDtoFull;
import ru.practicum.ewm.events.dto.EventMapper;
import ru.practicum.ewm.events.service.EventServiceImpl;
import ru.practicum.ewm.requests.dto.RequestDto;

import java.util.List;
import java.util.stream.Collectors;

import static ru.practicum.ewm.events.dto.EventMapper.toDtoResponseEvent;

@RestController
@RequestMapping(path = "/users/{userId}/events")
@RequiredArgsConstructor
public class EventControllerPrivate {
    private final EventServiceImpl eventService;

    @GetMapping
    List<EventDtoFull> getAllEventUser(@PathVariable long userId,
                                       @RequestParam(defaultValue = "0") Integer from,
                                       @RequestParam(defaultValue = "20") Integer size) {
        return eventService.getAllEventUser(userId, from, size).stream().map(EventMapper::toDtoResponseEvent).collect(Collectors.toList());
    }

    @PatchMapping
    public EventDtoFull updateEvent(@PathVariable long userId, @RequestBody EventDto eventDto) {
        return eventService.updateEvent(userId, eventDto);
    }

    @PostMapping
    public EventDtoFull createEvent(@PathVariable long userId, @RequestBody EventDto eventDto) {
        return toDtoResponseEvent(eventService.createEvent(userId, eventDto));
    }

    @GetMapping(path = "{eventId}")
    public EventDtoFull getEventUser(@PathVariable long userId, @PathVariable long eventId) {
        return toDtoResponseEvent(eventService.getEventUser(userId, eventId));
    }

    @PatchMapping(path = "{eventId}")
    public EventDtoFull cancelingUpdateEvent(@PathVariable long userId, @PathVariable long eventId) {
        return eventService.cancelingUpdateEvent(userId, eventId);
    }

    @GetMapping(path = "{eventId}/requests")
    public List<RequestDto> getRequestsEventUser(@PathVariable long userId, @PathVariable long eventId) {
        return eventService.getRequestsEventUser(userId, eventId);
    }

    @PatchMapping(path = "{eventId}/requests/{reqId}/confirm")
    public RequestDto confirmTheApplication(@PathVariable long userId, @PathVariable long eventId, @PathVariable long reqId) {
        return eventService.confirmTheApplication(userId, eventId, reqId);
    }

    @PatchMapping(path = "{eventId}/requests/{reqId}/reject")
    public RequestDto rejectTheApplication(@PathVariable long userId, @PathVariable long eventId, @PathVariable long reqId) {
        return eventService.rejectTheApplication(userId, eventId, reqId);
    }


}
