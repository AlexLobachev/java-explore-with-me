package ru.practicum.ewm.repositories.comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.ewm.models.comment.Comment;
import ru.practicum.ewm.models.comment.StateComment;

/**
 * Интерфейс репозиторий комментариев.
 *
 * @version 1.0
 * @autor Lobachev
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    /**
     * Метод - получение комментариев с возможностью фильтрации
     *
     * @param pageable - фильтр
     * @return Page<Comment> - страница с комментариями
     */

    Page<Comment> findAllByState(StateComment stateComment, Pageable pageable);

    /**
     * Метод - обновление комментария
     *
     * @param text   - текст комментария
     * @param idComm - id комментария который нужно обновить
     */
    @Modifying(clearAutomatically = true)
    @Query(value = "update Comment c set c.text =?1, c.state = 'PENDING' where c.id =?2")
    void updateComment(String text, long idComm);

    /**
     * Метод - публикация комментария
     *
     * @param idComm - id комментария который нужно обновить
     * @param stateComment - статус
     */
    @Modifying(clearAutomatically = true)
    @Query(value = "update Comment c set c.state =?2 where c.id =?1")
    void updateStatus(long idComm, StateComment stateComment);

}
