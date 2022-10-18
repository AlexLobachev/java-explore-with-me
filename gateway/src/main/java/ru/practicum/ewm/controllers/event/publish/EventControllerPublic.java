package ru.practicum.ewm.controllers.event.publish;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.practicum.ewm.client.event.publish.EventClientPublic;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Класс контроллер событий. (публичный)
 *
 * @version 1.0
 * @autor Lobachev
 */
@Controller
@RequestMapping(path = "/events")
@AllArgsConstructor
@Valid
public class EventControllerPublic {
    /**
     * Поле зависимость от сервисного класса EventClient
     */
    private final EventClientPublic eventClient;

    /**
     * Метод - Получение событий с возможностью фильтрации
     * Энпоинт - /events
     *
     * @param text          - текст для поиска в содержимом аннотации и подробном описании события
     * @param categories    - список идентификаторов категорий в которых будет вестись поиск
     * @param paid          - поиск только платных/бесплатных событий
     * @param rangeStart    - дата и время не раньше которых должно произойти событие
     * @param rangeEnd      - дата и время не позже которых должно произойти событие
     * @param onlyAvailable - только события у которых не исчерпан лимит запросов на участие
     * @param sort          - Вариант сортировки: по дате события или по количеству просмотров
     * @param from          - с какого место необходимо искать подборку
     * @param size          - количество для вывода
     * @param request       - получение id пользователя сделавшего запрос
     */
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

    /**
     * Метод - Получение подробной информации об опубликованном событии по его идентификатору
     * Энпоинт - /events/id
     *
     * @param id      - текст для поиска в содержимом аннотации и подробном описании события
     * @param request - получение id пользователя сделавшего запрос
     */
    @GetMapping(path = "{id}")
    public ResponseEntity<Object> getEventById(@PathVariable long id, HttpServletRequest request) {

        return eventClient.getEventById(id);
    }


}
