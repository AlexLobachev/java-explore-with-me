package ru.practicum.ewm.repositories.request;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.ewm.models.request.Request;
import ru.practicum.ewm.models.request.StatusRequest;

import java.util.List;

/**
 * Интерфейс репозиторий запросов.
 *
 * @version 1.0
 * @autor Lobachev
 */
@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    /**
     * Метод - получение всех запросов пользователей
     *
     * @param userId - id пользователя
     */
    List<Request> findAllByRequesterId(long userId);

    /**
     * Метод - получение запроса пользователя
     *
     * @param userId - id пользователя
     */
    Request findByRequesterId(long userId);

    /**
     * Метод - обновление статуса запроса
     *
     * @param statusRequest - статус
     * @param requestId     - id запроса
     */
    @Modifying(clearAutomatically = true)
    @Query("update Request r set " +
            "r.status =?2 where r.id =?1")
    void updateRequestStatus(long requestId, StatusRequest statusRequest);

    /**
     * Метод - обновление статуса запроса
     *
     * @param statusRequest - статус
     * @param eventId       - id события
     */
    @Modifying(clearAutomatically = true)
    @Query("update Request r set " +
            "r.status = ?2 where r.event.id =?1")
    void trowRequest(long eventId, StatusRequest statusRequest);

    /**
     * Метод - получение всех запросов пользователей
     *
     * @param userId  - id пользователя
     * @param eventId - id события
     */
    List<Request> findAllByRequesterIdAndEventId(long userId, long eventId);

    /**
     * Метод - получение запросов пользователя по событию
     *
     * @param eventId - id события
     */
    List<Request> findAllByEventId(long eventId);

}

