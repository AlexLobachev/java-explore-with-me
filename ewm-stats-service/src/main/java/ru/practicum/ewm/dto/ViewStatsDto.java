package ru.practicum.ewm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Класс ДТО статистики <b>app</b>, <b>app</b>, <b>uri</b>, <b>hits</b>.
 *
 * @version 1.0
 * @autor Lobachev
 */

@Data
@AllArgsConstructor
public class ViewStatsDto {
    /**
     * Поле идентификатор сервиса
     */
    private String app;
    /**
     * Поле URI
     */
    private String uri;
    /**
     * Поле количество просмотров
     */
    private long hits;
}
