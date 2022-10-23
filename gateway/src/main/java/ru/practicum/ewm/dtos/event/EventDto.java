package ru.practicum.ewm.dtos.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import ru.practicum.ewm.dtos.location.Location;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * Класс ДТО событий со свойствами:
 * <b>annotation</b>, <b>category</b>, <b>description</b>, <b>eventDate</b>,
 * * <b>location</b>, <b>paid</b>, <b>participantLimit</b>, <b>requestModeration</b>,
 * * <b>title</b>, <b>eventId</b>
 *
 * @version 1.0
 * @autor Lobachev
 */
@Getter
@Setter
public class EventDto {
    /**
     * Поле аннотация к событию
     */
    @Size(max = 2000, min = 20)
    private String annotation;
    /**
     * Поле категории события
     */
    @NotNull
    private long category;
    /**
     * Поле полное описание события
     */
    @Size(max = 7000, min = 20)
    private String description;
    /**
     * Поле дата и время на которые намечено событие
     */
    @FutureOrPresent
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;
    /**
     * Поле место события
     */

    private Location location;
    /**
     * Поле нужно ли оплачивать участие
     */
    @NotNull
    private Boolean paid;
    /**
     * Поле ограничение на количество участников
     */
    @NotNull
    private Integer participantLimit;
    /**
     * Поле нужна ли пре-модерация заявок на участие
     */
    @NotNull
    private Boolean requestModeration = false;
    /**
     * Поле заголовок
     */
    @Size(max = 120, min = 3)
    private String title;
    /**
     * Поле идентификатор
     */

    private long eventId;
}
