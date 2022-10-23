package ru.practicum.ewm.models.compilation;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

/**
 * Класс сущность - подборки событий со свойствами: <b>id</b>, <b>compilationId</b>, <b>eventId</b>
 *
 * @version 2.1
 * @autor Lobachev
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "compilations_events")
public class CompilationEvent {
    /**
     * Поле идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    /**
     * Поле идентификатор подборки
     */
    @Column(name = "compilation_id")
    private long compilationId;
    /**
     * Поле идентификатор события
     */
    @Column(name = "events_id")
    private long eventId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompilationEvent that = (CompilationEvent) o;
        return id == that.id && compilationId == that.compilationId && eventId == that.eventId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, compilationId, eventId);
    }
}
