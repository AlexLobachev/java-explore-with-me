package ru.practicum.ewm.events.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.events.EventClient;
import ru.practicum.ewm.events.dto.EventDto;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;


@Controller
@RequestMapping(path = "/users/{userId}/events")
@AllArgsConstructor
@Valid
public class EventControllerPrivate {
    private final EventClient eventClient;

    @GetMapping
    public ResponseEntity<Object> getAllEventUser(@PathVariable long userId,
                                                  @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                  @Positive @RequestParam(defaultValue = "20") Integer size) {
        return eventClient.getAllEventUser(userId, from, size);
    }

    @PatchMapping
    public ResponseEntity<Object> updateEvent(@PathVariable long userId, @Validated @RequestBody EventDto eventDto) {
        return eventClient.updateEvent(userId, eventDto);
    }

    @PostMapping
    public ResponseEntity<Object> createEvent(@PathVariable long userId, @RequestBody EventDto eventDto) {
        return eventClient.createEvent(userId, eventDto);
    }

    @GetMapping(path = "{eventId}")
    public ResponseEntity<Object> getEventUser(@PathVariable long userId, @PathVariable long eventId) {
        return eventClient.getEventUser(userId, eventId);
    }

    @PatchMapping(path = "{eventId}")
    public ResponseEntity<Object> cancelingUpdateEvent(@PathVariable long userId, @PathVariable long eventId) {
        return eventClient.cancelingUpdateEvent(userId, eventId);
    }

    @GetMapping(path = "{eventId}/requests")
    public ResponseEntity<Object> getRequestsEventUser(@PathVariable long userId, @PathVariable long eventId) {
        return eventClient.getRequestsEventUser(userId, eventId);
    }

    @PatchMapping(path = "{eventId}/requests/{reqId}/confirm")
    public ResponseEntity<Object> confirmTheApplication(@PathVariable long userId, @PathVariable long eventId, @PathVariable long reqId) {
        return eventClient.confirmTheApplication(userId, eventId, reqId);
    }

    @PatchMapping(path = "{eventId}/requests/{reqId}/reject")
    public ResponseEntity<Object> rejectTheApplication(@PathVariable long userId, @PathVariable long eventId, @PathVariable long reqId) {
        return eventClient.rejectTheApplication(userId, eventId, reqId);
    }


}
