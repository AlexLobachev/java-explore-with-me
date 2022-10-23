package ru.practicum.ewm.models.request;

/**
 * Инам - статус запроса: <b>PENDING</b>, <b>PUBLISHED</b>, <b>CANCELED</b>
 *
 * @version 2.1
 * @autor Lobachev
 */
public enum StatusRequest {
    /**
     * Черновик
     */
    PENDING,
    /**
     * Опубликован
     */
    PUBLISHED,
    /**
     * Отменен
     */
    CANCELED,
    /**
     * Подтвержден
     */
    CONFIRMED,
    /**
     * Отклонен
     */
    REJECTED
}
