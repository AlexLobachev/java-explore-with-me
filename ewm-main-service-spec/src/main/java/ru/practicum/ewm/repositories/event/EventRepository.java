package ru.practicum.ewm.repositories.event;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.ewm.models.category.Category;
import ru.practicum.ewm.models.event.Event;
import ru.practicum.ewm.models.event.State;
import ru.practicum.ewm.models.location.Location;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Интерфейс репозиторий событий.
 *
 * @version 1.0
 * @autor Lobachev
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    /**
     * Метод - получение всех событий инициатора
     *
     * @param id       - id подборки
     * @param pageable - правила выборки
     */
    Page<Event> findAllByInitiatorId(long id, Pageable pageable);

    /**
     * Метод - обновление событий
     *
     * @param id                - id события
     * @param annotation        - аннотация
     * @param category          - категория
     * @param description       - описание
     * @param eventDate         - дата события
     * @param paid              - статус оплачиваемая/нет
     * @param participantLimit  - лимит участников
     * @param title             - заголовок
     * @param statusEvent       - статус
     * @param requestModeration - модерация заявок
     */
    @Modifying(clearAutomatically = true)
    @Query("update Event e " +
            "set " +
            "e.annotation =?2, e.category =?3,e.description=?4," +
            "e.eventDate=?5,e.id=?6,e.paid=?7," +
            "e.participantLimit=?8, e.title=?9,e.state=?10,e.requestModeration =?11 where e.initiator.id = ?1")
    void updateEvent(long userId, String annotation, Category category,
                     String description, LocalDateTime eventDate, long id,
                     Boolean paid, Integer participantLimit, String title, State statusEvent, Boolean requestModeration);

    /**
     * Метод - редактирование событий
     *
     * @param eventId           - id события
     * @param annotation        - аннотация
     * @param category          - категория
     * @param description       - описание
     * @param eventDate         - дата события
     * @param location          - локация события
     * @param paid              - статус оплачиваемая/нет
     * @param participantLimit  - лимит участников
     * @param title             - заголовок
     * @param requestModeration - модерация заявок
     */
    @Modifying(clearAutomatically = true)
    @Query("update Event e " +
            "set " +
            "e.annotation =?2,e.category =?3,e.description =?4," +
            "e.eventDate=?5, e.location =?6, e.paid = ?7, e.participantLimit =?8, e.requestModeration =?9,e.title =?10 where e.id =?1")
    void editingAnEvent(long eventId, String annotation, long category,
                        String description, LocalDateTime eventDate,
                        Location location, Boolean paid, Integer participantLimit, Boolean requestModeration, String title);

    /**
     * Метод - получение события инициатором
     *
     * @param userId - id инициатора
     */
    Event findByInitiatorId(long userId);

    /**
     * Метод - получение всех событий инициатором
     *
     * @param userId - id инициатора
     */
    Event findByInitiatorIdAndId(long userId, long eventId);

    /**
     * Метод - обновление статуса
     *
     * @param eventId - id события
     * @param status  - статус
     */
    @Modifying(clearAutomatically = true)
    @Query("update Event e set e.state =?2 where e.id =?1")
    void cancelingUpdateEvent(long eventId, State status);

    /**
     * Метод - получение событий по заданным условиям
     *
     * @param text       - описание или анотация
     * @param categories - категория
     * @param rangeStart - дата от которой искать
     * @param rangeEnd   - дата до которой искать
     * @param paid       - статус оплаты
     * @param pageable   - правила выборки
     */
    @Query("select e" +
            " from Event e " +
            "where " +
            "(?1 is null or (lower(e.annotation) like  %?1% or  lower(e.description) like %?1%)) and " +
            "(?2 is null or e.category.id in ?2) and " +
            " (e.eventDate > ?3) and " +
            " (e.eventDate < ?4) and " +
            " (?5 is null or e.paid = ?5 )"
    )
    Page<Event> findPublicDate(String text,
                               List<Long> categories,
                               LocalDateTime rangeStart,
                               LocalDateTime rangeEnd,
                               Boolean paid,
                               Pageable pageable);

    /**
     * Метод - получение событий по заданным условиям
     *
     * @param users      - список пользователей
     * @param categories - категория
     * @param rangeStart - дата от которой искать
     * @param rangeEnd   - дата до которой искать
     * @param states     - статус
     * @param pageable   - правила выборки
     */
    @Query("" +
            " select e" +
            " from Event e where" +
            "?1 is null or e.initiator.id in    ?1          and" +
            "(?2 is null or e.state in          ?2)          and" +
            "(?3 is null or e.category.id in     ?3)          and " +
            "(e.eventDate >        ?4)          and " +
            "(e.eventDate <        ?5)")
    Page<Event> findAdmin(List<Long> users, List<String> states,
                          List<Integer> categories,
                          LocalDateTime rangeStart, LocalDateTime rangeEnd,
                          Pageable pageable);

    /**
     * Метод - обновление статуса
     *
     * @param eventId - id события
     * @param status  - статус
     */
    @Modifying(clearAutomatically = true)
    @Query("update Event e set " +
            "e.state =?2 where e.id =?1")
    void eventState(long eventId, State status);

    /**
     * Метод - счетчик посещений индивидуального события
     *
     * @param id - id события
     */
    @Modifying(clearAutomatically = true)
    @Query("update Event e set " +
            "e.views =e.views+1 where e.id =?1")
    void updateView(long id);

    /**
     * Метод - счетчик посещений всех событий
     */
    @Modifying(clearAutomatically = true)
    @Query("update Event e set " +
            "e.views =e.views+1")
    void updateViewAll();

    /**
     * Метод - добавить участника
     *
     * @param id - id события
     */
    @Modifying(clearAutomatically = true)
    @Query("update Event e set " +
            "e.confirmedRequests =e.confirmedRequests+1 where e.id =?1")
    void updateRequestsAdd(long id);

    /**
     * Метод - удалить участника
     *
     * @param id - id события
     */
    @Modifying(clearAutomatically = true)
    @Query("update Event e set " +
            "e.confirmedRequests =e.confirmedRequests-1 where e.id =?1")
    void updateRequestsDelete(long id);


}
