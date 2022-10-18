package ru.practicum.ewm.controllers.event.privat;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.client.event.privat.EventClientPrivate;
import ru.practicum.ewm.dtos.event.EventDto;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

/**
 * Класс контроллер событий. (приватный)
 *
 * @version 1.0
 * @autor Lobachev
 */
@Controller
@RequestMapping(path = "/users/{userId}/events")
@AllArgsConstructor
@Valid
public class EventControllerPrivate {
    /**
     * Поле зависимость от сервисного класса EventClient
     */
    private final EventClientPrivate eventClient;

    /**
     * Метод - Получение событий, добавленных текущим пользователем
     * Энпоинт - /users/id/events
     *
     * @param userId - id текущего пользователя
     * @param from   - с какого место необходимо искать подборку
     * @param size   - количество для вывода
     */
    @GetMapping
    public ResponseEntity<Object> getAllEventUser(@PathVariable long userId,
                                                  @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                  @Positive @RequestParam(defaultValue = "20") Integer size) {
        return eventClient.getAllEventUser(userId, from, size);
    }

    /**
     * Метод - Изменение события добавленного текущим пользователем
     * Энпоинт - /users/id/events
     *
     * @param userId   - id текущего пользователя
     * @param eventDto - параметры редактирования
     */
    @PatchMapping
    public ResponseEntity<Object> updateEvent(@PathVariable long userId, @Validated @RequestBody EventDto eventDto) {
        return eventClient.updateEvent(userId, eventDto);
    }

    /**
     * Метод - Добавление нового события
     * Энпоинт - /users/id/events
     *
     * @param userId   - id текущего пользователя
     * @param eventDto - параметры события
     */

    @PostMapping
    public ResponseEntity<Object> createEvent(@PathVariable long userId, @Validated @RequestBody EventDto eventDto) {
        return eventClient.createEvent(userId, eventDto);
    }

    /**
     * Метод - Получение полной информации о событии добавленном текущим пользователем
     * Энпоинт - /users/id/events
     *
     * @param userId  - id текущего пользователя
     * @param eventId - id события
     */
    @GetMapping(path = "{eventId}")
    public ResponseEntity<Object> getEventUser(@PathVariable long userId, @PathVariable long eventId) {
        return eventClient.getEventUser(userId, eventId);
    }

    /**
     * Метод - Отмена события добавленного текущим пользователем.
     * Энпоинт - /users/id/events/id
     *
     * @param userId  - id текущего пользователя
     * @param eventId - id события
     */
    @PatchMapping(path = "{eventId}")
    public ResponseEntity<Object> cancelingUpdateEvent(@PathVariable long userId, @PathVariable long eventId) {
        return eventClient.cancelingUpdateEvent(userId, eventId);
    }

    /**
     * Метод - Получение информации о запросах на участие в событии текущего пользователя.
     * Энпоинт - /users/id/events/id/requests
     *
     * @param userId  - id текущего пользователя
     * @param eventId - id события
     */
    @GetMapping(path = "{eventId}/requests")
    public ResponseEntity<Object> getRequestsEventUser(@PathVariable long userId, @PathVariable long eventId) {
        return eventClient.getRequestsEventUser(userId, eventId);
    }

    /**
     * Метод - Подтверждение чужой заявки на участие в событии текущего пользователя.
     * Энпоинт - /users/id/events/id/requests/id/confirm
     *
     * @param userId  - id текущего пользователя
     * @param eventId - id события
     * @param reqId   - id заявки, которую подтверждает текущий пользователь
     */
    @PatchMapping(path = "{eventId}/requests/{reqId}/confirm")
    public ResponseEntity<Object> confirmTheApplication(@PathVariable long userId, @PathVariable long eventId, @PathVariable long reqId) {
        return eventClient.confirmTheApplication(userId, eventId, reqId);
    }

    /**
     * Метод - Отклонение чужой заявки на участие в событии текущего пользователя.
     * Энпоинт - /users/id/events/id/requests/id/confirm
     *
     * @param userId  - id текущего пользователя
     * @param eventId - id события
     * @param reqId   - id заявки, которую подтверждает текущий пользователь
     */
    @PatchMapping(path = "{eventId}/requests/{reqId}/reject")
    public ResponseEntity<Object> rejectTheApplication(@PathVariable long userId, @PathVariable long eventId, @PathVariable long reqId) {
        return eventClient.rejectTheApplication(userId, eventId, reqId);
    }


}
