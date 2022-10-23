package ru.practicum.ewm.dtos.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import ru.practicum.ewm.dtos.location.LocationDto;
import ru.practicum.ewm.dtos.user.UserShortDto;
import ru.practicum.ewm.models.category.Category;
import ru.practicum.ewm.models.event.State;

import java.time.LocalDateTime;

/**
 * Класс ДТО событий со свойствами:
 * <b>id</b>, <b>annotation</b>, <b>category</b>, <b>confirmedRequests</b>,
 * <b>createdOn</b>, <b>description</b>, <b>eventDate</b>, <b>initiator</b>,
 * <b>location</b>, <b>paid</b>, <b>participantLimit</b>, <b>publishedOn</b>,
 * <b>requestModeration</b>, <b>title</b>, <b>state</b>, <b>views</b>
 *
 * @version 1.0
 * @autor Lobachev
 */
@Getter
@Setter
public class EventDtoFull {
    /**
     * Поле идентификатор
     */
    private long id;
    /**
     * Поле аннотация к событию
     */
    private String annotation;
    /**
     * Поле категории события
     */
    private Category category;
    /**
     * Поле количество одобренных заявок
     */
    private Integer confirmedRequests;
    /**
     * Поле дата и время создания события
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdOn;
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
     * Поле инициатор события
     */
    private UserShortDto initiator;
    /**
     * Поле место события
     */
    private LocationDto location;
    /**
     * Поле нужно ли оплачивать участие
     */
    private Boolean paid;
    /**
     * Поле ограничение на количество участников
     */
    private Integer participantLimit;
    /**
     * Поле дата и время публикации события
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime publishedOn;
    /**
     * Поле нужна ли пре-модерация заявок на участие
     */
    private Boolean requestModeration;
    /**
     * Поле заголовок
     */
    private String title;
    /**
     * Поле список состояний жизненного цикла события
     */
    private State state;
    /**
     * Поле количество просмотрев события
     */
    private long views;
}

