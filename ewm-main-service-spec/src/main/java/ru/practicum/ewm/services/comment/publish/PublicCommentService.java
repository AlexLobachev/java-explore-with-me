package ru.practicum.ewm.services.comment.publish;

import ru.practicum.ewm.models.comment.Comment;

import java.util.List;

/**
 * Интерфейс комментариев (публичный).
 *
 * @version 1.0
 * @autor Lobachev
 */
public interface PublicCommentService {
    /**
     * Метод - Создания комментария
     * Эндпоинт - /events/id/comment
     *
     * @param comment - тело комментария
     */
    Comment createComment(Comment comment, long idUser, long idEvent);

    /**
     * Метод - Обновления комментария
     * Эндпоинт - /events/id/comment
     *
     * @param comment - тело комментария
     */
    Comment updateComment(Comment comment, long idComm, long idUser);

    /**
     * Метод - Получения комментариев
     * Эндпоинт - events/comments
     *
     * @param from - с какого комментария вывести
     * @param size - количество комментариев
     */
    List<Comment> getComments(Integer from, Integer size);

    /**
     * Метод - Удаление комментария
     * Эндпоинт - events/id/comment
     *
     * @param idComm - id комментария который необходимо удалить
     */
    void deleteComment(long idComm, long idUser);

}
