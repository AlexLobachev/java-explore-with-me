package ru.practicum.ewm.models.location;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

/**
 * Класс сущность - локация со свойствами: <b>id</b>, <b>lat</b>, <b>lon</b>,
 *
 * @version 2.1
 * @autor Lobachev
 */
@Getter
@Setter
@Entity
@ToString
@Table(name = "locations")
public class Location {
    /**
     * Поле идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    /**
     * Поле широта
     */
    private double lat;
    /**
     * Поле долгота
     */
    private double lon;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return id == location.id && Double.compare(location.lat, lat) == 0 && Double.compare(location.lon, lon) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lat, lon);
    }
}
