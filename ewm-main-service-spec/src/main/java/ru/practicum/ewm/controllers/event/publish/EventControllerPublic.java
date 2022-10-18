package ru.practicum.ewm.controllers.event.publish;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.practicum.ewm.dtos.event.Hit;
import ru.practicum.ewm.models.event.Event;
import ru.practicum.ewm.services.event.publish.PublicEventServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Класс контроллер событий. (публичный)
 *
 * @version 1.0
 * @autor Lobachev
 */
@RestController
@RequestMapping(path = "/events")
@RequiredArgsConstructor
@Slf4j
public class EventControllerPublic {
    /**
     * Поле зависимость от сервисного класса EventServiceImpl
     */
    private final PublicEventServiceImpl eventService;
    /**
     * Поле зависимость синхронизация клиентов статистики и событий
     */
    private final RestTemplate restTemplate = new RestTemplate();

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

    /**
     * Метод - Получение подробной информации об опубликованном событии по его идентификатору
     * Энпоинт - /events/id
     *
     * @param id      - текст для поиска в содержимом аннотации и подробном описании события
     * @param request - получение id пользователя сделавшего запрос
     */
    @GetMapping(path = "{id}")
    public Event getEventById(@PathVariable long id, HttpServletRequest request) {
        addView(request);
        return eventService.getEventById(id);
    }

    /**
     * Метод - Передача данных на сервис статистики
     * Энпоинт - /events/id
     *
     * @param request - данные пользователя
     */
    private void addView(HttpServletRequest request) {
        Hit endpointHit = new Hit();
        endpointHit.setApp("ewm-main-service-spec");
        endpointHit.setUri(request.getRequestURI());
        endpointHit.setIp(request.getRemoteAddr());
        HttpEntity<Hit> requestEntity = new HttpEntity<>(endpointHit);
        restTemplate.exchange("http://localhost:9090/hit", HttpMethod.POST, requestEntity, Hit.class);
    }


}
