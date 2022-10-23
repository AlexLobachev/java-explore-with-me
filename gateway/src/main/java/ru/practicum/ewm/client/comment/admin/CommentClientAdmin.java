package ru.practicum.ewm.client.comment.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.ewm.client.base.BaseClient;
import ru.practicum.ewm.client.category.publish.CategoryClientPublic;

/**
 * Класс клиент комментариев (для администратора).
 *
 * @version 1.0
 * @autor Lobachev
 */
@Service
public class CommentClientAdmin extends BaseClient {
    /**
     * Поле константа URL
     */
    private static final String API_PREFIX = "/events/admin";

    /**
     * Конструктор - создание нового объекта
     *
     * @see CategoryClientPublic (String, RestTemplateBuilder )
     */
    @Autowired
    public CommentClientAdmin(@Value("${ewm-main-service-spec.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        )
        ;
    }

    /**
     * Метод - Публикация комментария
     *
     * @param idComm - id комментария
     */
    public ResponseEntity<Object> publishComment(long idComm) {

        return patch("/" + idComm);
    }

    /**
     * Метод - отклонение комментария
     *
     * @param idComm - id комментария
     */
    public ResponseEntity<Object> rejectComment(long idComm) {

        return patch("/" + idComm);
    }

}
