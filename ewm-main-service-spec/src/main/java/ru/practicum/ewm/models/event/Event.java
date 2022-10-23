package ru.practicum.ewm.models.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.practicum.ewm.models.category.Category;
import ru.practicum.ewm.models.location.Location;
import ru.practicum.ewm.models.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Класс сущность - событий со свойствами:
 * <b>id</b>, <b>annotation</b>, <b>category</b>, <b>confirmedRequests</b>,
 * <b>createdOn</b>, <b>description</b>, <b>eventDate</b>, <b>initiator</b>,
 * <b>location</b>, <b>paid</b>, <b>participantLimit</b>, <b>publishedOn</b>,
 * <b>requestModeration</b>, <b>title</b>, <b>state</b>, <b>views</b>
 *
 * @version 2.1
 * @autor Lobachev
 */
@Getter
@Setter
@Entity
@ToString
@Table(name = "events")
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {
    /**
     * Поле идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    /**
     * Поле аннотация к событию
     */
    private String annotation;
    /**
     * Поле категории события
     */
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    /**
     * Поле количество одобренных заявок
     */
    @Column(name = "confirmed_requests")
    private Integer confirmedRequests = 0;
    /**
     * Поле дата и время создания события
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_on")
    private LocalDateTime createdOn = LocalDateTime.now();
    /**
     * Поле полное описание события
     */
    private String description;
    /**
     * Поле дата и время на которые намечено событие
     */
    @Column(name = "event_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;
    /**
     * Поле инициатор события
     */
    @ManyToOne
    @JoinColumn(name = "initiator_id")
    private User initiator;
    /**
     * Поле место события
     */
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;
    /**
     * Поле нужно ли оплачивать участие
     */
    private Boolean paid;
    /**
     * Поле ограничение на количество участников
     */
    @Column(name = "participant_limit")
    private Integer participantLimit;
    /**
     * Поле дата и время публикации события
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "published_on")
    private LocalDateTime publishedOn = LocalDateTime.now();
    /**
     * Поле нужна ли пре-модерация заявок на участие
     */
    @Column(name = "request_moderation")
    private Boolean requestModeration = false;
    /**
     * Поле заголовок
     */
    private String title;
    /**
     * Поле список состояний жизненного цикла события
     */
    @Enumerated(EnumType.STRING)
    private State state;
    /**
     * Поле количество просмотрев события
     */
    private long views;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id == event.id && views == event.views && annotation.equals(event.annotation) && category.equals(event.category) && confirmedRequests.equals(event.confirmedRequests) && description.equals(event.description) && eventDate.equals(event.eventDate) && initiator.equals(event.initiator) && location.equals(event.location) && paid.equals(event.paid) && participantLimit.equals(event.participantLimit) && requestModeration.equals(event.requestModeration) && title.equals(event.title) && state == event.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, annotation, category, confirmedRequests, description, eventDate, initiator, location, paid, participantLimit, requestModeration, title, state, views);
    }
}
