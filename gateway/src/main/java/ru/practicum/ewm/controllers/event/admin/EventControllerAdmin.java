package ru.practicum.ewm.controllers.event.admin;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.client.event.admin.EventClientAdmin;
import ru.practicum.ewm.dtos.event.EventDto;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Класс контроллер событий. (для администратора)
 *
 * @version 1.0
 * @autor Lobachev
 */
@Controller
@RequestMapping(path = "/admin/events")
@AllArgsConstructor
@Valid
public class EventControllerAdmin {
    /**
     * Поле зависимость от сервисного класса EventClient
     */
    private final EventClientAdmin eventClient;

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

    /**
     * Метод - Редактирование данных любого события администратором.
     * Энпоинт - /admin/events/id
     *
     * @param eventId  - id события
     * @param eventDto - параметры редактирования
     */
    @PutMapping(path = "{eventId}")
    public ResponseEntity<Object> editingAnEvent(@PathVariable long eventId, @Validated @RequestBody EventDto eventDto) {
        return eventClient.editingAnEvent(eventId, eventDto);
    }

    /**
     * Метод - Публикация события.
     * Энпоинт - /admin/events/id/reject
     *
     * @param eventId - id события
     */
    @PatchMapping(path = "{eventId}/reject")
    public ResponseEntity<Object> rejectionEvent(@PathVariable long eventId) {
        return eventClient.rejectionEvent(eventId);
    }

    /**
     * Метод - Отклонение события.
     * Энпоинт - /admin/events/id/reject
     *
     * @param eventId - id события
     */
    @PatchMapping(path = "{eventId}/publish")
    public ResponseEntity<Object> publishEvent(@PathVariable long eventId) {
        return eventClient.publishEvent(eventId);
    }
}
