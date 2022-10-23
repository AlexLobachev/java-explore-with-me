package ru.practicum.ewm.services.comment.admin;

import ru.practicum.ewm.models.comment.Comment;

/**
 * Интерфейс комментариев (для администратора).
 *
 * @version 1.0
 * @autor Lobachev
 */
public interface AdminCommentService {
    /**
     * Метод - Публикация комментария
     *
     * @param idComm - id комментария
     */
    Comment publishComment(long idComm);

    /**
     * Метод - отклонение комментария
     *
     * @param idComm - id комментария
     */
    Comment rejectComment(long idComm);
}
