package ru.practicum.ewm.dtos.category;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

/**
 * Класс ДТО категорий со свойствами: <b>id</b>, <b>name</b>.
 *
 * @version 1.0
 * @autor Lobachev
 */
@Getter
@Setter
public class CategoryDto {
    /**
     * Поле идентификатор
     */
    private long id;
    /**
     * Поле имя категории
     */
    @Size(min = 1)
    private String name;
}
