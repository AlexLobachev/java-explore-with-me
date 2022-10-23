package ru.practicum.ewm.client.comment.publish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.ewm.client.base.BaseClient;
import ru.practicum.ewm.client.category.publish.CategoryClientPublic;
import ru.practicum.ewm.dtos.comment.CommentDto;

import java.util.Map;

/**
 * Класс клиент комментариев (публичный).
 *
 * @version 1.0
 * @autor Lobachev
 */
@Service
public class CommentClientPublic extends BaseClient {
    /**
     * Поле константа URL
     */
    private static final String API_PREFIX = "/events";

    /**
     * Конструктор - создание нового объекта
     *
     * @see CategoryClientPublic (String, RestTemplateBuilder )
     */
    @Autowired
    public CommentClientPublic(@Value("${ewm-main-service-spec.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        )
        ;
    }

    /**
     * Метод - Создания комментария
     *
     * @param commentDto - тело комментария
     */
    public ResponseEntity<Object> createComment(CommentDto commentDto, long idUser, long idEvent) {

        return post("/" + idEvent + "/comment", idUser, commentDto);
    }

    /**
     * Метод - Обновления комментария
     *
     * @param commentDto - тело комментария
     */
    public ResponseEntity<Object> updateComment(CommentDto commentDto, long idComm, long idUser) {
        return patch("/" + idComm + "/comment", idUser, commentDto);
    }

    /**
     * Метод - Получения комментариев (только ОПУБЛИКОВАННЫХ)
     *
     * @param from - с какого комментария вывести
     * @param size - количество комментариев
     */
    public ResponseEntity<Object> getComments(Integer from, Integer size) {
        Map<String, Object> parameters = Map.of(
                "from", from,
                "size", size
        );
        return get("/comments", parameters);
    }

    /**
     * Метод - Удаление комментария
     *
     * @param idComm - id комментария который необходимо удалить
     */
    public ResponseEntity<Object> deleteComment(long idComm, long idUser) {
        return delete("/" + idComm, idUser);
    }

}
