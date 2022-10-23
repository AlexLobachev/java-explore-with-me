package ru.practicum.ewm.dtos.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import ru.practicum.ewm.models.location.Location;

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
    private String annotation;
    /**
     * Поле категории события
     */
    private long category;
    /**
     * Поле полное описание события
     */
    private String description;
    /**
     * Поле дата и время на которые намечено событие
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;
    /**
     * Поле место события
     */
    private Location location;
    /**
     * Поле нужно ли оплачивать участие
     */
    private Boolean paid;
    /**
     * Поле ограничение на количество участников
     */
    private Integer participantLimit;
    /**
     * Поле нужна ли пре-модерация заявок на участие
     */
    private Boolean requestModeration = false;
    /**
     * Поле заголовок
     */
    private String title;
    /**
     * Поле идентификатор
     */
    private long eventId;
}




