package ru.practicum.ewm.events;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.ewm.categories.model.Category;
import ru.practicum.ewm.events.model.Event;
import ru.practicum.ewm.events.model.Location;
import ru.practicum.ewm.events.model.State;

import java.time.LocalDateTime;
import java.util.List;

@Repository

public interface EventRepository extends JpaRepository<Event, Long> {
    Page<Event> findAllByInitiatorId(long id, Pageable pageable);

    @Modifying(clearAutomatically = true)
    @Query("update Event e " +
            "set " +
            "e.annotation =?2, e.category =?3,e.description=?4," +
            "e.eventDate=?5,e.id=?6,e.paid=?7," +
            "e.participantLimit=?8, e.title=?9,e.state=?10,e.requestModeration =?11 where e.initiator.id = ?1")
    void updateEvent(long userId, String annotation, Category category,
                     String description, LocalDateTime eventDate, long id,
                     Boolean paid, Integer participantLimit, String title, State statusEvent, Boolean requestModeration);


    @Modifying(clearAutomatically = true)
    @Query("update Event e " +
            "set " +
            "e.annotation =?2,e.category =?3,e.description =?4," +
            "e.eventDate=?5, e.location =?6, e.paid = ?7, e.participantLimit =?8, e.requestModeration =?9,e.title =?10 where e.id =?1")
    void editingAnEvent(long eventId, String annotation, long category,
                        String description, LocalDateTime eventDate,
                        Location location, Boolean paid, Integer participantLimit, Boolean requestModeration, String title);


    Event findByInitiatorId(long userId);

    Event findByInitiatorIdAndId(long userId, long eventId);

    @Modifying(clearAutomatically = true)
    @Query("update Event e set e.state =?2 where e.id =?1")
    void cancelingUpdateEvent(long eventId, State status);

    @Query("select e" +
            " from Event e " +
            "where " +
            "(?1 is null or (lower(e.annotation) like  %?1% or  lower(e.description) like %?1%)) and " +
            "(?2 is null or e.category.id in ?2) and " +
            " (?3 is null or e.eventDate > ?3) and " +
            " (?4 is null or e.eventDate < ?4) and " +
            " (?5 is null or e.paid = ?5 )"
    )
    Page<Event> findPublicDate(String text,
                               List<Long> categories,
                               LocalDateTime rangeStart,
                               LocalDateTime rangeEnd,
                               Boolean paid,
                               Pageable pageable);

    @Query("" +
            " select e" +
            " from Event e where" +
            "?1 is null or e.initiator.id in    ?1          and" +
            "(?2 is null or e.state in          ?2)          and" +
            "(?3 is null or e.category.id in     ?3)          and " +
            "(?4 is null or e.eventDate >        ?4)          and " +
            "(?5 is null or e.eventDate <        ?5)")
    Page<Event> findAdmin(List<Long> users, List<String> states,
                          List<Integer> categories,
                          LocalDateTime rangeStart, LocalDateTime rangeEnd,
                          Pageable pageable);


    @Modifying(clearAutomatically = true)
    @Query("update Event e set " +
            "e.state =?2 where e.id =?1")
    void eventState(long eventId, State status);

    @Modifying(clearAutomatically = true)
    @Query("update Event e set " +
            "e.views =e.views+1 where e.id =?1")
    void updateView(long id);

    @Modifying(clearAutomatically = true)
    @Query("update Event e set " +
            "e.views =e.views+1")
    void updateViewAll();

    @Modifying(clearAutomatically = true)
    @Query("update Event e set " +
            "e.confirmedRequests =e.confirmedRequests+1 where e.id =?1")
    void updateRequestsAdd(long id);

    @Modifying(clearAutomatically = true)
    @Query("update Event e set " +
            "e.confirmedRequests =e.confirmedRequests-1 where e.id =?1")
    void updateRequestsDelete(long id);


}
