package ru.practicum.ewm.requests;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.requests.dto.RequestDto;
import ru.practicum.ewm.requests.dto.RequestMapper;
import ru.practicum.ewm.requests.service.RequestServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import static ru.practicum.ewm.requests.dto.RequestMapper.requestToDto;

@RestController
@RequestMapping(path = "/users/{userId}/requests")
@RequiredArgsConstructor
public class RequestController {
    private final RequestServiceImpl requestService;

    @GetMapping
    public List<RequestDto> getRequestUserAlienEvent(@PathVariable long userId) {
        return requestService.getRequestUserAlienEvent(userId).stream().map(RequestMapper::requestToDto).collect(Collectors.toList());
    }

    @PostMapping
    public RequestDto createRequest(@PathVariable long userId, @RequestParam long eventId) {

        return requestToDto(requestService.createRequest(userId, eventId));
    }

    @PatchMapping(path = "{requestId}/cancel")
    public RequestDto cancelRequest(@PathVariable long userId, @PathVariable long requestId) {
        return requestService.cancelRequest(userId, requestId);
    }


}
