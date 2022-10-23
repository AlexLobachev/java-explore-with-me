package ru.practicum.ewm.controllers.request;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dtos.request.RequestDto;
import ru.practicum.ewm.mappers.request.RequestMapper;
import ru.practicum.ewm.services.request.RequestServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import static ru.practicum.ewm.mappers.request.RequestMapper.requestToDto;

/**
 * Класс контроллер запросов.
 *
 * @version 1.0
 * @autor Lobachev
 */
@RestController
@RequestMapping(path = "/users/{userId}/requests")
@RequiredArgsConstructor
public class RequestController {
    /**
     * Поле зависимость от сервисного класса RequestServiceImpl
     */
    private final RequestServiceImpl requestService;

    /**
     * Метод - Получение информации о заявках текущего пользователя на участие в чужих событиях
     * Энпоинт - /users/id/requests
     *
     * @param userId - id текущего пользователя
     */
    @GetMapping
    public List<RequestDto> getRequestUserAlienEvent(@PathVariable long userId) {
        return requestService.getRequestUserAlienEvent(userId).stream().map(RequestMapper::requestToDto).collect(Collectors.toList());
    }

    /**
     * Метод - Получение информации о заявках текущего пользователя на участие в чужих событиях
     * Энпоинт - /users/id/requests
     *
     * @param userId  - id текущего пользователя
     * @param eventId - id события
     */
    @PostMapping
    public RequestDto createRequest(@PathVariable long userId, @RequestParam long eventId) {

        return requestToDto(requestService.createRequest(userId, eventId));
    }

    /**
     * Метод - Отмена своего запроса на участие в событии
     * Энпоинт - /users/id/requests/id/cancel
     *
     * @param userId    - id текущего пользователя
     * @param requestId - id события
     */
    @PatchMapping(path = "{requestId}/cancel")
    public RequestDto cancelRequest(@PathVariable long userId, @PathVariable long requestId) {
        return requestService.cancelRequest(userId, requestId);
    }


}
