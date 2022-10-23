package ru.practicum.ewm.dtos.comment;

import lombok.Data;

import javax.validation.constraints.Size;

/**
 * Класс ДТО комментариев: <b>id</b>, <b>text</b>.
 *
 * @version 1.0
 * @autor Lobachev
 */
@Data
public class CommentDto {
    /**
     * Поле идентификатор
     */
    private long id;
    /**
     * Поле комментарий (так же модерируется админом на цензуру)
     */
    @Size(min = 1)
    @Size(max = 2000)
    private String text;

}
