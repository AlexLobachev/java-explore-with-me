package ru.practicum.ewm.client.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.ewm.client.base.BaseClient;
import ru.practicum.ewm.client.compilation.publish.CompilationClientPublic;
import ru.practicum.ewm.dtos.user.UserDto;

import java.util.List;
import java.util.Map;

/**
 * Класс клиент пользователей.
 *
 * @version 1.0
 * @autor Lobachev
 */
@Service

public class UserClient extends BaseClient {

    /**
     * Поле константа URL
     */
    private static final String API_PREFIX = "/admin/users";

    /**
     * Конструктор - создание нового объекта
     *
     * @see CompilationClientPublic (String, RestTemplateBuilder )
     */
    @Autowired
    public UserClient(@Value("${ewm-main-service-spec.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        )
        ;
    }

    /**
     * Метод - Добавление нового пользователя
     *
     * @param userDto - данные пользователя
     */
    public ResponseEntity<Object> createUser(UserDto userDto) {
        return post("", userDto);
    }

    /**
     * Метод - Получение информации о пользователях
     *
     * @param ids  - id пользователей
     * @param from - с какого место необходимо искать подборку
     * @param size - количество для вывода
     */

    public ResponseEntity<Object> getAllUsers(List<Long> ids, Integer from, Integer size) {
        Map<String, Object> parameters = Map.of(
                "ids", ids,
                "from", from,
                "size", size
        );
        return get("", parameters);
    }

    /**
     * Метод - Получение пользователя по id
     *
     * @param userId - id пользователя
     */

    public ResponseEntity<Object> getUser(long userId) {

        return get("/" + userId);
    }

    /**
     * Метод - Удаление пользователя
     *
     * @param id - id пользователя
     */
    public ResponseEntity<Object> deleteUser(Long id) {
        return delete("/" + id);
    }


}
