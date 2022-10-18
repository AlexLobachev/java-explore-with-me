package ru.practicum.ewm.dtos.location;

import lombok.Getter;
import lombok.Setter;

/**
 * Класс ДТО локаций со свойствами:
 * <b>lat</b>, <b>lon</b>
 *
 * @version 1.0
 * @autor Lobachev
 */
@Getter
@Setter
public class LocationDto {
    /**
     * Поле широта
     */
    private double lat;
    /**
     * Поле долгота
     */
    private double lon;
}
