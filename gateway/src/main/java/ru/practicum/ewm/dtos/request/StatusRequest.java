package ru.practicum.ewm.dtos.request;

/**
 * Инам - статус запроса: <b>PENDING</b>, <b>PUBLISHED</b>, <b>CANCELED</b>
 *
 * @version 2.1
 * @autor Lobachev
 */
public enum StatusRequest {
    /**
     * Опубликован
     */
    PENDING,
    /**
     * Отменен
     */
    CANCELED
}
