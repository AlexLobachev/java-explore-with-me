package ru.practicum.ewm.dtos.event;

import lombok.Getter;
import lombok.Setter;
import ru.practicum.ewm.dtos.user.UserShortDto;
import ru.practicum.ewm.models.category.Category;

import java.time.LocalDateTime;

/**
 * Класс ДТО событий со свойствами:
 * <b>annotation</b>, <b>category</b>, <b>confirmedRequests</b>, <b>eventDate</b>,
 * <b>id</b>, <b>initiator</b>, <b>paid</b>, <b>title</b>,
 * <b>views</b>
 *
 * @version 1.0
 * @autor Lobachev
 */
@Getter
@Setter
public class EventShortDto {
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
     * Поле дата и время на которые намечено событие
     */
    private LocalDateTime eventDate;
    /**
     * Поле идентификатор
     */
    private long id;
    /**
     * Поле инициатор события
     */
    private UserShortDto initiator;
    /**
     * Поле нужно ли оплачивать участие
     */
    private Boolean paid;
    /**
     * Поле заголовок
     */
    private String title;
    /**
     * Поле количество просмотрев события
     */
    private long views;

}
