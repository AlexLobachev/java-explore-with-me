package ru.practicum.ewm.dtos.user;

import lombok.Getter;
import lombok.Setter;

/**
 * Класс ДТО пользователей со свойствами:
 * <b>id</b>, <b>name</b>
 *
 * @version 1.0
 * @autor Lobachev
 */
@Getter
@Setter
public class UserShortDto {
    /**
     * Поле идентификатор
     */
    private long id;
    /**
     * Поле имя
     */
    private String name;
}

