package ru.practicum.ewm.events.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.practicum.ewm.events.dto.Hit;
import ru.practicum.ewm.events.model.Event;
import ru.practicum.ewm.events.service.EventServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/events")
@RequiredArgsConstructor
@Slf4j
public class EventControllerPublic {
    private final EventServiceImpl eventService;
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping
    public List<Event> getAllEvent(@RequestParam(required = false) String text,
                                   @RequestParam(required = false) List<Long> categories,
                                   @RequestParam(required = false) Boolean paid,
                                   @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeStart,
                                   @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeEnd,
                                   @RequestParam(required = false) Boolean onlyAvailable,
                                   @RequestParam(required = false) String sort,
                                   @RequestParam(defaultValue = "0") Integer from,
                                   @RequestParam(defaultValue = "20") Integer size, HttpServletRequest request) {
        addView(request);
        return eventService.getAllEvent(text, categories,
                paid, rangeStart,
                rangeEnd,
                onlyAvailable, sort,
                from, size);
    }

    @GetMapping(path = "{id}")
    public Event getEventById(@PathVariable long id, HttpServletRequest request) {
        addView(request);
        return eventService.getEventById(id);
    }

    private void addView(HttpServletRequest request) {
        Hit endpointHit = new Hit();
        endpointHit.setApp("ewm-main-service-spec");
        endpointHit.setUri(request.getRequestURI());
        endpointHit.setIp(request.getRemoteAddr());
        HttpEntity<Hit> requestEntity = new HttpEntity<>(endpointHit);
        restTemplate.exchange("http://localhost:9090/hit", HttpMethod.POST, requestEntity, Hit.class);
    }


}
