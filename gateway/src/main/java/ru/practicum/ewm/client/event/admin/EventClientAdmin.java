package ru.practicum.ewm.client.event.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.ewm.client.base.BaseClient;
import ru.practicum.ewm.client.compilation.publish.CompilationClientPublic;
import ru.practicum.ewm.dtos.event.EventDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Класс клиент событий. (для администратора)
 *
 * @version 1.0
 * @autor Lobachev
 */
@Service
public class EventClientAdmin extends BaseClient {
    /**
     * Поле константа URL
     */
    private static final String API_PREFIX = "";

    /**
     * Конструктор - создание нового объекта
     *
     * @see CompilationClientPublic (String, RestTemplateBuilder )
     */
    @Autowired
    public EventClientAdmin(@Value("${ewm-main-service-spec.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        )
        ;
    }

    /**
     * Метод - получение всех событий подходящих под переданные условия
     * Энпоинт - /admin/events
     *
     * @param users1      - список id пользователей, чьи события нужно найти
     * @param states1     - список состояний в которых находятся искомые события
     * @param categories1 - список id категорий в которых будет вестись поиск
     * @param rangeStart1 - дата и время не раньше которых должно произойти событие
     * @param rangeEnd1   - дата и время не позже которых должно произойти событие
     * @param from        - с какого место необходимо искать подборку
     * @param size        - количество для вывода
     */
    public ResponseEntity<Object> getAllEventUser(List<Long> users1, List<String> states1,
                                                  List<Integer> categories1,
                                                  LocalDateTime rangeStart1, LocalDateTime rangeEnd1,
                                                  Integer from, Integer size) {

        String users = users1.stream().map(Object::toString)
                .collect(Collectors.joining(" "));
        String states = states1.stream().map(Object::toString)
                .collect(Collectors.joining(" "));
        String categories = categories1.stream().map(Object::toString)
                .collect(Collectors.joining(" "));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String rangeEnd = rangeEnd1.format(formatter);
        String rangeStart = rangeStart1.format(formatter);

        Map<String, Object> parameters = Map.of(
                "users", users,
                "states", states,
                "categories", categories,
                "rangeStart", rangeStart,
                "rangeEnd", rangeEnd,
                "from", from,
                "size", size
        );

        return get("/admin/events?users={users}&states={states}&categories={categories}&rangeStart={rangeStart}&rangeEnd={rangeEnd}&from={from}&size={size}", parameters);
    }

    /**
     * Метод - Редактирование данных любого события администратором.
     * Энпоинт - /admin/events/id
     *
     * @param eventId  - id события
     * @param eventDto - параметры редактирования
     */
    public ResponseEntity<Object> editingAnEvent(long eventId, EventDto eventDto) {

        return put("/admin/events/" + eventId, eventDto);
    }

    /**
     * Метод - Публикация события.
     * Энпоинт - /admin/events/id/reject
     *
     * @param eventId - id события
     */
    public ResponseEntity<Object> rejectionEvent(long eventId) {

        return patch("/admin/events/" + eventId + "/reject", eventId);
    }

    /**
     * Метод - Отклонение события.
     * Энпоинт - /admin/events/id/reject
     *
     * @param eventId - id события
     */
    public ResponseEntity<Object> publishEvent(long eventId) {
        return patch("/admin/events/" + eventId + "/publish", eventId);
    }
}
