package ru.practicum.ewm.events.controller;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.events.EventClient;
import ru.practicum.ewm.events.dto.EventDto;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.List;


@Controller
@RequestMapping(path = "/admin/events")
@AllArgsConstructor
@Valid
public class EventControllerAdmin {
    private final EventClient eventClient;

    @GetMapping
    public ResponseEntity<Object> getAllEventUser(@RequestParam(required = false) List<Long> users,
                                                  @RequestParam(required = false) List<String> states,
                                                  @RequestParam(required = false) List<Integer> categories,
                                                  @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeStart,
                                                  @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeEnd,
                                                  @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                  @Positive @RequestParam(defaultValue = "20") Integer size) {
        return eventClient.getAllEventUser(
                users, states,
                categories,
                rangeStart, rangeEnd,
                from,
                size);
    }

    @PutMapping(path = "{eventId}")
    public ResponseEntity<Object> editingAnEvent(@PathVariable long eventId, @Validated @RequestBody EventDto eventDto) {
        return eventClient.editingAnEvent(eventId, eventDto);
    }

    @PatchMapping(path = "{eventId}/reject")
    public ResponseEntity<Object> rejectionEvent(@PathVariable long eventId) {
        return eventClient.rejectionEvent(eventId);
    }

    @PatchMapping(path = "{eventId}/publish")
    public ResponseEntity<Object> publishEvent(@PathVariable long eventId) {
        return eventClient.publishEvent(eventId);
    }
}
