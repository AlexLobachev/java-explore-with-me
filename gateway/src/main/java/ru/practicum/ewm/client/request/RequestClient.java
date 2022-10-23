package ru.practicum.ewm.client.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.ewm.client.base.BaseClient;
import ru.practicum.ewm.client.compilation.publish.CompilationClientPublic;
import ru.practicum.ewm.dtos.request.RequestDto;

import java.util.Map;

/**
 * Класс клиент запросов.
 *
 * @version 1.0
 * @autor Lobachev
 */
@Service
public class RequestClient extends BaseClient {
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
    public RequestClient(@Value("${ewm-main-service-spec.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        )
        ;
    }

    /**
     * Метод - Получение информации о заявках текущего пользователя на участие в чужих событиях
     * Энпоинт - /users/id/requests
     *
     * @param userId - id текущего пользователя
     */
    public ResponseEntity<Object> getRequestUserAlienEvent(long userId) {

        return get("/users/" + userId + "/requests");
    }

    /**
     * Метод - Получение информации о заявках текущего пользователя на участие в чужих событиях
     * Энпоинт - /users/id/requests
     *
     * @param userId  - id текущего пользователя
     * @param eventId - id события
     */
    public ResponseEntity<Object> createRequest(long userId, long eventId) {
        RequestDto requestDto = new RequestDto();
        Map<String, Object> parameters = Map.of(
                "eventId", eventId

        );
        return post("/users/" + userId + "/requests?eventId={eventId}", userId, parameters, requestDto);
    }

    /**
     * Метод - Отмена своего запроса на участие в событии
     * Энпоинт - /users/id/requests/id/cancel
     *
     * @param userId    - id текущего пользователя
     * @param requestId - id события
     */
    public ResponseEntity<Object> cancelRequest(long userId, long requestId) {
        return patch("/users/" + userId + "/requests/" + requestId + "/cancel");
    }


}
