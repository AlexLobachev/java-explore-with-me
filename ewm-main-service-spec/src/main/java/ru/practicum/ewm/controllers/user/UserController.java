package ru.practicum.ewm.controllers.user;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.models.user.User;
import ru.practicum.ewm.services.user.UserServiceImpl;

import javax.validation.Valid;
import java.util.List;

/**
 * Класс контроллер пользователей.
 *
 * @version 1.0
 * @autor Lobachev
 */
@RestController
@RequestMapping(path = "/admin/users")
@RequiredArgsConstructor
@Valid
public class UserController {
    /**
     * Поле зависимость от сервисного класса UserServiceImpl
     */
    private final UserServiceImpl userService;

    /**
     * Метод - Добавление нового пользователя
     * Энпоинт - /admin/users
     *
     * @param user - данные пользователя
     */
    @PostMapping
    public User createUser(@Validated @RequestBody User user) {

        return userService.createUser(user);
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
    public List<User> getAllUsers(@RequestParam(required = false) List<Long> ids,
                                  @RequestParam(defaultValue = "0") Integer from,
                                  @RequestParam(defaultValue = "20") Integer size) {
        return userService.getAllUsers(ids, from, size);
    }

    /**
     * Метод - Получение пользователя по id
     * Энпоинт - /admin/users/id
     *
     * @param userId - id пользователя
     */
    @GetMapping(value = "{userId}")
    public User getUser(@PathVariable long userId) {
        return userService.getUser(userId);
    }

    /**
     * Метод - Удаление пользователя
     * Энпоинт - /admin/users/id
     *
     * @param userId - id пользователя
     */
    @DeleteMapping(value = "{userId}")
    public void deleteUser(@PathVariable long userId) {
        userService.deleteUser(userId);
    }
}
