package ru.practicum.ewm.models.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.practicum.ewm.models.event.Event;
import ru.practicum.ewm.models.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Класс сущность - запроса со свойствами: <b>id</b>, <b>requester</b>, <b>event</b>, <b>created</b>, <b>status</b>
 *
 * @version 2.1
 * @autor Lobachev
 */
@Getter
@Setter
@Entity
@ToString
@Table(name = "requests")
@NoArgsConstructor
public class Request {
    /**
     * Поле идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    /**
     * Поле пользователь
     */
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User requester;
    /**
     * Поле событие
     */
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
    /**
     * Поле дата и время создания заявки
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created = LocalDateTime.now();
    /**
     * Поле статус заявки
     */
    @Enumerated(EnumType.STRING)
    private StatusRequest status = StatusRequest.PENDING;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return id == request.id && Objects.equals(requester, request.requester) && Objects.equals(event, request.event) && Objects.equals(created, request.created) && status == request.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, requester, event, created, status);
    }
}
