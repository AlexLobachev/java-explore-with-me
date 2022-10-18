package ru.practicum.ewm.dtos.compilation;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @NotNull
    private List<Long> events;
    /**
     * Поле признака публикации на гл. странице
     */
    @NotNull
    private Boolean pinned;
    /**
     * Поле описания подборок событий
     */
    @NotBlank
    private String title;
}
