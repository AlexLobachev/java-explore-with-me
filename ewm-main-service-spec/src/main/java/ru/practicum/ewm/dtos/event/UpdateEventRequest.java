package ru.practicum.ewm.dtos.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import ru.practicum.ewm.models.category.Category;

import java.time.LocalDateTime;

/**
 * Класс ДТО событий со свойствами:
 * <b>annotation</b>, <b>category</b>, <b>description</b>, <b>eventDate</b>,
 * <b>id</b>, <b>paid</b>, <b>title</b>
 *
 * @version 1.0
 * @autor Lobachev
 */
@Getter
@Setter
public class UpdateEventRequest {
    /**
     * Поле аннотация к событию
     */
    private String annotation;
    /**
     * Поле категории события
     */
    private Category category;
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
     * Поле идентификатор
     */
    private long id;
    /**
     * Поле нужно ли оплачивать участие
     */
    private Boolean paid;
    /**
     * Поле заголовок
     */
    private String title;
}
