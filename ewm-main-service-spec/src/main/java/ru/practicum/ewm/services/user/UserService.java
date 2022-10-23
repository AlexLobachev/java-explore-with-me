package ru.practicum.ewm.services.user;


import ru.practicum.ewm.models.user.User;

import java.util.List;

/**
 * Интерфейс пользователей.
 *
 * @version 1.0
 * @autor Lobachev
 */
public interface UserService {
    /**
     * Метод - Добавление нового пользователя
     *
     * @param user - данные пользователя
     */
    User createUser(User user);

    /**
     * Метод - Получение информации о пользователях
     *
     * @param ids  - id пользователей
     * @param from - с какого место необходимо искать подборку
     * @param size - количество для вывода
     */
    List<User> getAllUsers(List<Long> ids, Integer from, Integer size);

    /**
     * Метод - Получение пользователя по id
     *
     * @param userId - id пользователя
     */
    User getUser(long userId);

    /**
     * Метод - Удаление пользователя
     *
     * @param userId - id пользователя
     */
    void deleteUser(long userId);
}
