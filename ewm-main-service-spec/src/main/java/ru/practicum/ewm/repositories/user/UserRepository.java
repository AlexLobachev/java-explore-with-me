package ru.practicum.ewm.repositories.user;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.ewm.models.user.User;

import java.util.List;

/**
 * Интерфейс репозиторий пользователей.
 *
 * @version 1.0
 * @autor Lobachev
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Метод - получение всех пользователей
     *
     * @param ids      - id пользователей
     * @param pageable - правила выборки
     */
    @Query("select u from User u where u.id in ?1")
    List<User> findAll(List<Long> ids, Pageable pageable);
}
