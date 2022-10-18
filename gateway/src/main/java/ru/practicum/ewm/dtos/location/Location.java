package ru.practicum.ewm.dtos.location;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * Класс ДТО локаций со свойствами:
 * <b>lat</b>, <b>lon</b>
 *
 * @version 1.0
 * @autor Lobachev
 */
@Getter
@Setter
public class Location {
    /**
     * Поле широта
     */
    @NotNull
    private double lat;
    /**
     * Поле долгота
     */
    @NotNull
    private double lon;
}
