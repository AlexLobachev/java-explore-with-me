package ru.practicum.ewm.controllers.event.privat;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dtos.event.EventDto;
import ru.practicum.ewm.dtos.event.EventDtoFull;
import ru.practicum.ewm.dtos.request.RequestDto;
import ru.practicum.ewm.mappers.event.EventMapper;
import ru.practicum.ewm.services.event.privat.PrivateEventServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import static ru.practicum.ewm.mappers.event.EventMapper.toDtoResponseEvent;

/**
 * Класс контроллер событий. (приватный)
 *
 * @version 1.0
 * @autor Lobachev
 */
@RestController
@RequestMapping(path = "/users/{userId}/events")
@RequiredArgsConstructor
public class EventControllerPrivate {
    /**
     * Поле зависимость от сервисного класса EventServiceImpl
     */
    private final PrivateEventServiceImpl eventService;

    /**
     * Метод - Получение событий, добавленных текущим пользователем
     * Энпоинт - /users/id/events
     *
     * @param userId - id текущего пользователя
     * @param from   - с какого место необходимо искать подборку
     * @param size   - количество для вывода
     */
    @GetMapping
    List<EventDtoFull> getAllEventUser(@PathVariable long userId,
                                       @RequestParam(defaultValue = "0") Integer from,
                                       @RequestParam(defaultValue = "20") Integer size) {
        return eventService.getAllEventUser(userId, from, size).stream().map(EventMapper::toDtoResponseEvent).collect(Collectors.toList());
    }

    /**
     * Метод - Изменение события добавленного текущим пользователем
     * Энпоинт - /users/id/events
     *
     * @param userId   - id текущего пользователя
     * @param eventDto - параметры редактирования
     */
    @PatchMapping
    public EventDtoFull updateEvent(@PathVariable long userId, @RequestBody EventDto eventDto) {
        return eventService.updateEvent(userId, eventDto);
    }

    /**
     * Метод - Добавление нового события
     * Энпоинт - /users/id/events
     *
     * @param userId   - id текущего пользователя
     * @param eventDto - параметры события
     */
    @PostMapping
    public EventDtoFull createEvent(@PathVariable long userId, @RequestBody EventDto eventDto) {
        return toDtoResponseEvent(eventService.createEvent(userId, eventDto));
    }

    /**
     * Метод - Получение полной информации о событии добавленном текущим пользователем
     * Энпоинт - /users/id/events
     *
     * @param userId  - id текущего пользователя
     * @param eventId - id события
     */
    @GetMapping(path = "{eventId}")
    public EventDtoFull getEventUser(@PathVariable long userId, @PathVariable long eventId) {
        return toDtoResponseEvent(eventService.getEventUser(userId, eventId));
    }

    /**
     * Метод - Отмена события добавленного текущим пользователем.
     * Энпоинт - /users/id/events/id
     *
     * @param userId  - id текущего пользователя
     * @param eventId - id события
     */
    @PatchMapping(path = "{eventId}")
    public EventDtoFull cancelingUpdateEvent(@PathVariable long userId, @PathVariable long eventId) {
        return eventService.cancelingUpdateEvent(userId, eventId);
    }

    /**
     * Метод - Получение информации о запросах на участие в событии текущего пользователя.
     * Энпоинт - /users/id/events/id/requests
     *
     * @param userId  - id текущего пользователя
     * @param eventId - id события
     */
    @GetMapping(path = "{eventId}/requests")
    public List<RequestDto> getRequestsEventUser(@PathVariable long userId, @PathVariable long eventId) {
        return eventService.getRequestsEventUser(userId, eventId);
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
    public RequestDto confirmTheApplication(@PathVariable long userId, @PathVariable long eventId, @PathVariable long reqId) {
        return eventService.confirmTheApplication(userId, eventId, reqId);
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
    public RequestDto rejectTheApplication(@PathVariable long userId, @PathVariable long eventId, @PathVariable long reqId) {
        return eventService.rejectTheApplication(userId, eventId, reqId);
    }


}
