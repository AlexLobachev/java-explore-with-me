package ru.practicum.ewm.client.compilation.publish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.ewm.client.base.BaseClient;

import java.util.Map;

/**
 * Класс клиент подборок событий. (публичный)
 *
 * @version 1.0
 * @autor Lobachev
 */
@Service
public class CompilationClientPublic extends BaseClient {
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
    public CompilationClientPublic(@Value("${ewm-main-service-spec.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        )
        ;
    }

    /**
     * Метод - получение всех подборок
     *
     * @param pinned - условие поиска (закрепленные/незакрепленные подборки)
     * @param from   - с какого место необходимо искать подборку
     * @param size   - количество для вывода
     */
    public ResponseEntity<Object> getAllCompilation(Boolean pinned, Integer from, Integer size) {
        Map<String, Object> parameters = Map.of(
                "pinned", pinned,
                "from", from,
                "size", size
        );
        return get("/compilations?pinned={pinned}&from={from}&size={size}", parameters);
    }

    /**
     * Метод - получение подборки по id
     *
     * @param compId - id подборки
     */
    public ResponseEntity<Object> getCompilationById(long compId) {

        return get("/compilations/" + compId);
    }
}
