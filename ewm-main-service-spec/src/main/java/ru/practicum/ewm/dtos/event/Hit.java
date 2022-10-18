package ru.practicum.ewm.dtos.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Класс ДТО статистики:
 * <b>id</b>, <b>app</b>, <b>uri</b>, <b>ip</b> <b>timestamp</b>
 *
 * @version 1.0
 * @autor Lobachev
 */
@Data
public class Hit {
    /**
     * Поле идентификатор
     */
    private long id;
    /**
     * Поле идентификатор сервиса
     */
    private String app;
    /**
     * Поле URI
     */
    private String uri;
    /**
     * Поле ip-адрес пользователя
     */
    private String ip;
    /**
     * Поле дата просмотра события
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp = LocalDateTime.now();
}
