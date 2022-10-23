package ru.practicum.ewm.dtos.comment;

import lombok.Getter;
import lombok.Setter;
import ru.practicum.ewm.models.comment.StateComment;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Класс ДТО комментариев: <b>text</b>, <b>owner</b>, <b>event</b>, <b>dateComment</b>.
 *
 * @version 1.0
 * @autor Lobachev
 */
@Getter
@Setter
public class CommentDtoAdmin {
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
     * Поле список состояний жизненного цикла комментария
     */
    @Enumerated(EnumType.STRING)
    private StateComment state = StateComment.PENDING;
}
