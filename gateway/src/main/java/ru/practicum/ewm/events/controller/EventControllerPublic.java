package ru.practicum.ewm.events.controller;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.practicum.ewm.events.EventClient;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping(path = "/events")
@AllArgsConstructor
@Valid
public class EventControllerPublic {
    private final EventClient eventClient;

    @GetMapping
    public ResponseEntity<Object> getAllEvent(@RequestParam(required = false) String text,
                                              @RequestParam(required = false) List<Long> categories,
                                              @RequestParam(required = false) Boolean paid,
                                              @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeStart,
                                              @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeEnd,
                                              @RequestParam(required = false) Boolean onlyAvailable,
                                              @RequestParam(required = false) String sort,
                                              @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                              @Positive @RequestParam(defaultValue = "20") Integer size, HttpServletRequest request) {

        return eventClient.getAllEvent(text, categories,
                paid, rangeStart,
                rangeEnd,
                onlyAvailable, sort,
                from, size);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Object> getEventById(@PathVariable long id, HttpServletRequest request) {

        return eventClient.getEventById(id);
    }


}
