package ru.practicum.ewm.events.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.events.dto.EventDto;
import ru.practicum.ewm.events.dto.EventDtoFull;
import ru.practicum.ewm.events.dto.EventMapper;
import ru.practicum.ewm.events.model.Event;
import ru.practicum.ewm.events.service.EventServiceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/admin/events")
@RequiredArgsConstructor
public class EventControllerAdmin {
    private final EventServiceImpl eventService;

    @GetMapping
    public List<EventDtoFull> getAllEventUser(@RequestParam(required = false) List<Long> users,
                                              @RequestParam(required = false) List<String> states,
                                              @RequestParam(required = false) List<Integer> categories,
                                              @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeStart,
                                              @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeEnd,
                                              @RequestParam(defaultValue = "0") Integer from,
                                              @RequestParam(defaultValue = "20") Integer size) {
        return eventService.getAllEventUser(
                users, states,
                categories,
                rangeStart, rangeEnd,
                from,
                size).stream().map(EventMapper::toDtoResponseEvent).collect(Collectors.toList());
    }

    @PutMapping(path = "{eventId}")
    public EventDtoFull editingAnEvent(@PathVariable long eventId, @RequestBody EventDto eventDto) {
        return eventService.editingAnEvent(eventId, eventDto);
    }

    @PatchMapping(path = "{eventId}/reject")
    public EventDtoFull rejectionEvent(@PathVariable long eventId) {
        return eventService.rejectionEvent(eventId);
    }

    @PatchMapping(path = "{eventId}/publish")
    public Event publishEvent(@PathVariable long eventId) {
        return eventService.publishEvent(eventId);
    }
}
