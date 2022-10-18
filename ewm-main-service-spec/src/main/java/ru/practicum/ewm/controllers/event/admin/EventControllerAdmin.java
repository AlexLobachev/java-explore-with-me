package ru.practicum.ewm.controllers.event.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dtos.event.EventDto;
import ru.practicum.ewm.dtos.event.EventDtoFull;
import ru.practicum.ewm.mappers.event.EventMapper;
import ru.practicum.ewm.models.event.Event;
import ru.practicum.ewm.services.event.admin.AdminEventServiceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс контроллер событий. (для администратора)
 *
 * @version 1.0
 * @autor Lobachev
 */
@RestController
@RequestMapping(path = "/admin/events")
@RequiredArgsConstructor
public class EventControllerAdmin {
    /**
     * Поле зависимость от сервисного класса EventServiceImpl
     */
    private final AdminEventServiceImpl eventService;

    /**
     * Метод - получение всех событий подходящих под переданные условия
     * Энпоинт - /admin/events
     *
     * @param users      - список id пользователей, чьи события нужно найти
     * @param states     - список состояний в которых находятся искомые события
     * @param categories - список id категорий в которых будет вестись поиск
     * @param rangeStart - дата и время не раньше которых должно произойти событие
     * @param rangeEnd   - дата и время не позже которых должно произойти событие
     * @param from       - с какого место необходимо искать подборку
     * @param size       - количество для вывода
     */
    @GetMapping
    public List<EventDtoFull> getAllEventUser(@RequestParam(required = false) List<Long> users,
                                              @RequestParam(required = false) List<String> states,
                                              @RequestParam(required = false) List<Integer> categories,
                                              @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeStart,
                                              @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeEnd,
                                              @RequestParam(defaultValue = "0") Integer from,
                                              @RequestParam(defaultValue = "20") Integer size) {
        return eventService.getAllEventUser(
                users, states,
                categories,
                rangeStart, rangeEnd,
                from,
                size).stream().map(EventMapper::toDtoResponseEvent).collect(Collectors.toList());
    }

    /**
     * Метод - Редактирование данных любого события администратором.
     * Энпоинт - /admin/events/id
     *
     * @param eventId  - id события
     * @param eventDto - параметры редактирования
     */
    @PutMapping(path = "{eventId}")
    public EventDtoFull editingAnEvent(@PathVariable long eventId, @RequestBody EventDto eventDto) {
        return eventService.editingAnEvent(eventId, eventDto);
    }

    /**
     * Метод - Публикация события.
     * Энпоинт - /admin/events/id/reject
     *
     * @param eventId - id события
     */
    @PatchMapping(path = "{eventId}/reject")
    public EventDtoFull rejectionEvent(@PathVariable long eventId) {
        return eventService.rejectionEvent(eventId);
    }

    /**
     * Метод - Отклонение события.
     * Энпоинт - /admin/events/id/reject
     *
     * @param eventId - id события
     */
    @PatchMapping(path = "{eventId}/publish")
    public Event publishEvent(@PathVariable long eventId) {
        return eventService.publishEvent(eventId);
    }
}
