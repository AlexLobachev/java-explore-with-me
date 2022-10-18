package ru.practicum.ewm.client.event.privat;

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

import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * Класс клиент событий. (приватный)
 *
 * @version 1.0
 * @autor Lobachev
 */
@Service
public class EventClientPrivate extends BaseClient {
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
    public EventClientPrivate(@Value("${ewm-main-service-spec.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        )
        ;
    }

    /**
     * Метод - Получение событий, добавленных текущим пользователем
     *
     * @param userId - id текущего пользователя
     * @param from   - с какого место необходимо искать подборку
     * @param size   - количество для вывода
     */
    public ResponseEntity<Object> getAllEventUser(long userId, Integer from, Integer size) {
        Map<String, Object> parameters = Map.of(
                "from", from,
                "size", size);
        return get("/users/" + userId + "/events?from={from}&size={size}", parameters);
    }

    /**
     * Метод - Изменение события добавленного текущим пользователем
     *
     * @param userId   - id текущего пользователя
     * @param eventDto - параметры редактирования
     */
    public ResponseEntity<Object> updateEvent(long userId, EventDto eventDto) {
        checkEventDate(eventDto);
        return patch("/users/" + userId + "/events", eventDto);
    }

    /**
     * Метод - Добавление нового события
     *
     * @param userId   - id текущего пользователя
     * @param eventDto - параметры события
     */

    public ResponseEntity<Object> createEvent(long userId, EventDto eventDto) {
        checkEventDate(eventDto);
        return post("/users/" + userId + "/events", eventDto);
    }

    /**
     * Метод - Получение полной информации о событии добавленном текущим пользователем
     *
     * @param userId  - id текущего пользователя
     * @param eventId - id события
     */
    public ResponseEntity<Object> getEventUser(long userId, long eventId) {

        return get("/users/" + userId + "/events/" + eventId);
    }

    /**
     * Метод - Отмена события добавленного текущим пользователем.
     *
     * @param userId  - id текущего пользователя
     * @param eventId - id события
     */
    public ResponseEntity<Object> cancelingUpdateEvent(long userId, long eventId) {
        return patch("/users/" + userId + "/events/" + eventId);
    }

    /**
     * Метод - Получение информации о запросах на участие в событии текущего пользователя.
     *
     * @param userId  - id текущего пользователя
     * @param eventId - id события
     */
    public ResponseEntity<Object> getRequestsEventUser(long userId, long eventId) {
        return get("/users/" + userId + "/events/" + eventId + "/requests");
    }

    /**
     * Метод - Подтверждение чужой заявки на участие в событии текущего пользователя.
     *
     * @param userId  - id текущего пользователя
     * @param eventId - id события
     * @param reqId   - id заявки, которую подтверждает текущий пользователь
     */
    public ResponseEntity<Object> confirmTheApplication(long userId, long eventId, long reqId) {
        return patch("/users/" + userId + "/events/" + eventId + "/requests/" + reqId + "/confirm");

    }

    /**
     * Метод - Отклонение чужой заявки на участие в событии текущего пользователя.
     *
     * @param userId  - id текущего пользователя
     * @param eventId - id события
     * @param reqId   - id заявки, которую подтверждает текущий пользователь
     */
    public ResponseEntity<Object> rejectTheApplication(long userId, long eventId, long reqId) {

        return patch("/users/" + userId + "/events/" + eventId + "/requests/" + reqId + "/reject");
    }

    /**
     * Метод - проверки даты
     *
     * @param eventDto - параметры события
     */
    private void checkEventDate(EventDto eventDto) {

        if (eventDto.getEventDate().isBefore(LocalDateTime.now().plusHours(2))) {
            throw new ValidationException("Дата и время на которые намечено событие не может быть раньше, чем через два часа от текущего момента");
        }

    }
}
