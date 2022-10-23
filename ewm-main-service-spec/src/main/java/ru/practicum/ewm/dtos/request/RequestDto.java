package ru.practicum.ewm.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import ru.practicum.ewm.models.request.StatusRequest;

import java.time.LocalDateTime;

/**
 * Класс ДТО запросов со свойствами:
 * <b>created</b>, <b>event</b>, <b>id</b>, <b>requester</b>, <b>status</b>
 *
 * @version 1.0
 * @autor Lobachev
 */
@Getter
@Setter
public class RequestDto {
    /**
     * Поле дата и время создания заявки
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;
    /**
     * Поле id события
     */
    private long event;
    /**
     * Поле идентификатор
     */
    private long id;
    /**
     * Поле id пользователя
     */
    private long requester;
    /**
     * Поле статус заявки
     */
    private StatusRequest status;
}
