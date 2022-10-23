package ru.practicum.ewm.dtos.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
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
     * Поле id события
     */
    private long id;
    /**
     * Поле дата и время создания заявки
     */
    @NotNull
    private LocalDateTime created = LocalDateTime.now();
    /**
     * Поле статус заявки
     */
    @NotNull
    private StatusRequest status = StatusRequest.PENDING;
}
