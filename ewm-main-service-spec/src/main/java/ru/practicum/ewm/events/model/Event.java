package ru.practicum.ewm.events.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.practicum.ewm.categories.model.Category;
import ru.practicum.ewm.users.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Entity
@ToString
@Table(name = "events")
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String annotation;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @Column(name = "confirmed_requests")
    private Integer confirmedRequests = 0;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_on")
    private LocalDateTime createdOn = LocalDateTime.now();
    private String description;
    @Column(name = "event_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;
    @ManyToOne
    @JoinColumn(name = "initiator_id")
    private User initiator;
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;
    private Boolean paid;
    @Column(name = "participant_limit")
    private Integer participantLimit;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "published_on")
    private LocalDateTime publishedOn = LocalDateTime.now();
    @Column(name = "request_moderation")
    private Boolean requestModeration = false;
    private String title;
    @Enumerated(EnumType.STRING)
    private State state;
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
