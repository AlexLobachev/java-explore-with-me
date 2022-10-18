package ru.practicum.ewm.dtos.compilation;

import lombok.Getter;
import lombok.Setter;
import ru.practicum.ewm.dtos.event.EventShortDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс ДТО подборок событий со свойствами:<b>id</b>, <b>events</b>, <b>pinned</b>, <b>title</b>.
 *
 * @version 1.0
 * @autor Lobachev
 */
@Getter
@Setter
public class CompilationDtoEvent {
    /**
     * Поле список событий
     */
    private List<EventShortDto> events = new ArrayList<>();
    /**
     * Поле идентификатор
     */
    private long id;
    /**
     * Поле признака публикации на гл. странице
     */
    private Boolean pinned;
    /**
     * Поле описания подборок событий
     */
    private String title;
}
