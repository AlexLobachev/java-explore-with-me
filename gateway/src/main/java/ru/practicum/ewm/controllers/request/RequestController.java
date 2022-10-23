package ru.practicum.ewm.controllers.request;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.client.request.RequestClient;

import javax.validation.Valid;

/**
 * Класс контроллер запросов.
 *
 * @version 1.0
 * @autor Lobachev
 */
@Controller
@RequestMapping(path = "/users/{userId}/requests")
@AllArgsConstructor
@Valid
public class RequestController {
    /**
     * Поле зависимость от сервисного класса RequestClient
     */
    private final RequestClient requestClient;

    /**
     * Метод - Получение информации о заявках текущего пользователя на участие в чужих событиях
     * Энпоинт - /users/id/requests
     *
     * @param userId - id текущего пользователя
     */
    @GetMapping
    public ResponseEntity<Object> getRequestUserAlienEvent(@PathVariable long userId) {
        return requestClient.getRequestUserAlienEvent(userId);
    }

    /**
     * Метод - Получение информации о заявках текущего пользователя на участие в чужих событиях
     * Энпоинт - /users/id/requests
     *
     * @param userId  - id текущего пользователя
     * @param eventId - id события
     */
    @PostMapping
    public ResponseEntity<Object> createRequest(@PathVariable long userId, @RequestParam long eventId) {

        return requestClient.createRequest(userId, eventId);
    }

    /**
     * Метод - Отмена своего запроса на участие в событии
     * Энпоинт - /users/id/requests/id/cancel
     *
     * @param userId    - id текущего пользователя
     * @param requestId - id события
     */
    @PatchMapping(path = "{requestId}/cancel")
    public ResponseEntity<Object> cancelRequest(@PathVariable long userId, @PathVariable long requestId) {
        return requestClient.cancelRequest(userId, requestId);
    }


}
