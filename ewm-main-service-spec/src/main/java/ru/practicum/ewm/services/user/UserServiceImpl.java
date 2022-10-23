package ru.practicum.ewm.services.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.exceptions.ExceptionNotFound;
import ru.practicum.ewm.models.user.User;
import ru.practicum.ewm.pageable.OffsetLimitPageable;
import ru.practicum.ewm.repositories.user.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс бизнес логики пользователей.
 *
 * @version 1.0
 * @autor Lobachev
 */
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    /**
     * Поле зависимость от репозитория пользователей UserRepository
     */
    private final UserRepository userRepository;

    /**
     * Метод - Добавление нового пользователя
     *
     * @param user - данные пользователя
     */
    public User createUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Метод - Получение информации о пользователях
     *
     * @param ids  - id пользователей
     * @param from - с какого место необходимо искать подборку
     * @param size - количество для вывода
     */
    public List<User> getAllUsers(List<Long> ids, Integer from, Integer size) {
        Pageable pageable = OffsetLimitPageable.of(from, size, Sort.by("id"));
        if (ids == null) {
            return userRepository.findAll(pageable).stream().collect(Collectors.toList());
        }
        return userRepository.findAll(ids, pageable);
    }

    /**
     * Метод - Получение пользователя по id
     *
     * @param userId - id пользователя
     */

    public User getUser(long userId) {
        User user = userRepository.findById(userId).orElse(new User());
        if (user.getName() == null) {
            throw new ExceptionNotFound("Пользователь не найден");
        }
        return user;
    }

    /**
     * Метод - Удаление пользователя
     *
     * @param userId - id пользователя
     */
    public void deleteUser(long userId) {
        getUser(userId);
        userRepository.deleteById(userId);
    }
}
