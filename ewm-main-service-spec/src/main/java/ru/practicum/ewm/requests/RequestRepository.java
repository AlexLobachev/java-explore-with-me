package ru.practicum.ewm.requests;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.ewm.requests.model.Request;
import ru.practicum.ewm.requests.model.StatusRequest;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findAllByRequesterId(long userId);

    Request findByRequesterId(long userId);

    @Modifying(clearAutomatically = true)
    @Query("update Request r set " +
            "r.status =?2 where r.id =?1")
    void updateRequestStatus(long requestId, StatusRequest statusRequest);


    @Modifying(clearAutomatically = true)
    @Query("update Request r set " +
            "r.status = ?2 where r.event.id =?1")
    void trowRequest(long eventId, StatusRequest status);

    List<Request> findAllByRequesterIdAndEventId(long userId, long eventId);

    List<Request> findAllByEventId(long eventId);

}

