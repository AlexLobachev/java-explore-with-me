package ru.practicum.ewm.requests;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping(path = "/users/{userId}/requests")
@AllArgsConstructor
@Valid
public class RequestController {
    private final RequestClient requestClient;

    @GetMapping
    public ResponseEntity<Object> getRequestUserAlienEvent(@PathVariable long userId) {
        return requestClient.getRequestUserAlienEvent(userId);
    }

    @PostMapping
    public ResponseEntity<Object> createRequest(@PathVariable long userId, @RequestParam long eventId) {

        return requestClient.createRequest(userId, eventId);
    }

    @PatchMapping(path = "{requestId}/cancel")
    public ResponseEntity<Object> cancelRequest(@PathVariable long userId, @PathVariable long requestId) {
        return requestClient.cancelRequest(userId, requestId);
    }


}
