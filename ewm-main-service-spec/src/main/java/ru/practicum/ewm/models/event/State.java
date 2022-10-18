package ru.practicum.ewm.models.event;

/**
 * Инам - статус событий: <b>PENDING</b>, <b>PUBLISHED</b>, <b>CANCELED</b>
 *
 * @version 2.1
 * @autor Lobachev
 */
public enum State {
    /**
     * Черновик
     */
    PENDING,
    /**
     * Опубликовано
     */
    PUBLISHED,
    /**
     * Отклонено
     */
    CANCELED
}
