package ru.practicum.ewm.dtos.comment;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Класс ДТО комментариев: <b>text</b>, <b>owner</b>, <b>event</b>, <b>dateComment</b>.
 *
 * @version 1.0
 * @autor Lobachev
 */
@Getter
@Setter
public class CommentDto {
    /**
     * Поле комментарий (так же модерируется админом на цензуру)
     */
    private String text;
    /**
     * Поле пользователь оставивший комментарий
     */
    private long owner;
    /**
     * Поле событие
     */
    private long event;
    /**
     * Поле дата комментария
     */
    private LocalDateTime dateComment = LocalDateTime.now();

}
