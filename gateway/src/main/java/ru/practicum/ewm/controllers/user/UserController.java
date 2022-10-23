package ru.practicum.ewm.controllers.user;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.client.user.UserClient;
import ru.practicum.ewm.dtos.user.UserDto;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

/**
 * Класс контроллер пользователей.
 *
 * @version 1.0
 * @autor Lobachev
 */
@Controller
@RequestMapping(path = "admin/users")
@AllArgsConstructor
@Valid
public class UserController {
    /**
     * Поле зависимость от сервисного класса UserClient
     */
    private final UserClient userClient;

    /**
     * Метод - Добавление нового пользователя
     * Энпоинт - /admin/users
     *
     * @param userDto - данные пользователя
     */
    @PostMapping
    public ResponseEntity<Object> createUser(@Validated @RequestBody UserDto userDto) {

        return userClient.createUser(userDto);
    }

    /**
     * Метод - Получение информации о пользователях
     * Энпоинт - /admin/users
     *
     * @param ids  - id пользователей
     * @param from - с какого место необходимо искать подборку
     * @param size - количество для вывода
     */
    @GetMapping
    public ResponseEntity<Object> getAllUsers(@RequestParam(required = false) List<Long> ids,
                                              @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                              @Positive @RequestParam(defaultValue = "20") Integer size) {
        return userClient.getAllUsers(ids, from, size);
    }

    /**
     * Метод - Получение пользователя по id
     * Энпоинт - /admin/users/id
     *
     * @param userId - id пользователя
     */
    @GetMapping(value = "{userId}")
    public ResponseEntity<Object> getUser(@PathVariable long userId) {
        return userClient.getUser(userId);
    }

    /**
     * Метод - Удаление пользователя
     * Энпоинт - /admin/users/id
     *
     * @param userId - id пользователя
     */
    @DeleteMapping(value = "{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable long userId) {
        return userClient.deleteUser(userId);

    }

}
