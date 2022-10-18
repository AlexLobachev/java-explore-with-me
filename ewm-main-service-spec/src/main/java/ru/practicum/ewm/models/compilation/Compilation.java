package ru.practicum.ewm.models.compilation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.practicum.ewm.models.event.Event;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Класс сущность - подборок событий со свойствами: <b>id</b>, <b>events</b>, <b>pinned</b>, <b>title</b>.
 *
 * @version 2.1
 * @autor Киса Воробьянинов
 */
@Getter
@Setter
@Entity
@ToString
@Table(name = "compilations")
@NoArgsConstructor
public class Compilation {
    /**
     * Поле идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    /**
     * Поле список событий
     */
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Event> events = new ArrayList<>();
    /**
     * Поле признака публикации на гл. странице
     */
    private Boolean pinned = false;
    /**
     * Поле описания подборок событий
     */
    @Size(min = 1)
    private String title;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Compilation that = (Compilation) o;
        return id == that.id && Objects.equals(events, that.events) && Objects.equals(pinned, that.pinned) && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, events, pinned, title);
    }
}
