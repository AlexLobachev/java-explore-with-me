package ru.practicum.ewm.client.category.publish;

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
 * Класс клиент категорий.
 *
 * @version 1.0
 * @autor Lobachev
 */
@Service
public class CategoryClientPublic extends BaseClient {
    /**
     * Поле константа URL
     */
    private static final String API_PREFIX = "";

    /**
     * Конструктор - создание нового объекта
     *
     * @see CategoryClientPublic (String, RestTemplateBuilder )
     */
    @Autowired
    public CategoryClientPublic(@Value("${ewm-main-service-spec.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        )
        ;
    }

    /**
     * Метод - получения списка событий
     *
     * @param from - с какого события начать поиск
     * @param size - ограничение кличества выборки событий
     */
    public ResponseEntity<Object> getAllCategories(Integer from, Integer size) {
        Map<String, Object> parameters = Map.of(
                "from", from,
                "size", size
        );
        return get("/categories", parameters);
    }

    /**
     * Метод - получения событий по id
     * Энпоинт - /categories
     *
     * @param catId - id нужного события
     */
    public ResponseEntity<Object> getCategoryById(long catId) {
        return get("/categories/" + catId);
    }
}
