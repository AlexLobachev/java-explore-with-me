package ru.practicum.ewm.client.event.publish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.ewm.client.base.BaseClient;
import ru.practicum.ewm.client.compilation.publish.CompilationClientPublic;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Класс клиент событий. (публичный)
 *
 * @version 1.0
 * @autor Lobachev
 */
@Service
public class EventClientPublic extends BaseClient {
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
    public EventClientPublic(@Value("${ewm-main-service-spec.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        )
        ;
    }

    /**
     * Метод - Получение событий с возможностью фильтрации
     * Энпоинт - /events
     *
     * @param text          - текст для поиска в содержимом аннотации и подробном описании события
     * @param categories1   - список идентификаторов категорий в которых будет вестись поиск
     * @param paid          - поиск только платных/бесплатных событий
     * @param rangeStart1   - дата и время не раньше которых должно произойти событие
     * @param rangeEnd1     - дата и время не позже которых должно произойти событие
     * @param onlyAvailable - только события у которых не исчерпан лимит запросов на участие
     * @param sort          - Вариант сортировки: по дате события или по количеству просмотров
     * @param from          - с какого место необходимо искать подборку
     * @param size          - количество для вывода
     */
    public ResponseEntity<Object> getAllEvent(String text, List<Long> categories1,
                                              Boolean paid, LocalDateTime rangeStart1,
                                              LocalDateTime rangeEnd1,
                                              Boolean onlyAvailable, String sort,
                                              Integer from, Integer size) {

        String categories = categories1.stream().map(Object::toString)
                .collect(Collectors.joining(" "));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String rangeEnd = rangeEnd1.format(formatter);
        String rangeStart = rangeStart1.format(formatter);


        Map<String, Object> parameters = Map.of(
                "text", text,
                "categories", categories,
                "paid", paid,
                "rangeStart", rangeStart,
                "rangeEnd", rangeEnd,
                "onlyAvailable", onlyAvailable,
                "sort", sort,
                "from", from,
                "size", size
        );

        return get("/events?text={text}&categories={categories}&paid={paid}&rangeStart={rangeStart}&rangeEnd={rangeEnd}&onlyAvailable={onlyAvailable}&sort={sort}&from={from}&size={size}", parameters);
    }

    /**
     * Метод - Получение подробной информации об опубликованном событии по его идентификатору
     *
     * @param id - текст для поиска в содержимом аннотации и подробном описании события
     */
    public ResponseEntity<Object> getEventById(long id) {
        return get("/events/" + id);
    }

}
