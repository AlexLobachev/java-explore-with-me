package ru.practicum.ewm.dtos.compilation;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Класс ДТО подборок событий со свойствами: <b>events</b>, <b>pinned</b>, <b>title</b>.
 *
 * @version 1.0
 * @autor Lobachev
 */
@Getter
@Setter
public class CompilationDto {
    /**
     * Поле список id событий
     */
    private List<Long> events;
    /**
     * Поле признака публикации на гл. странице
     */
    private Boolean pinned;
    /**
     * Поле описания подборок событий
     */
    private String title;
}
