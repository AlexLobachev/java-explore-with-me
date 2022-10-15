package ru.practicum.ewm.compilations.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "compilations_events")
public class CompilationEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "compilation_id")
    private long compilationId;
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
