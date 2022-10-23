package ru.practicum.ewm.services.comment.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.models.comment.Comment;
import ru.practicum.ewm.models.comment.StateComment;
import ru.practicum.ewm.repositories.comment.CommentRepository;

/**
 * Класс бизнес логики комментариев (для администратора).
 *
 * @version 1.0
 * @autor Lobachev
 */
@RequiredArgsConstructor
@Transactional
@Service
public class AdminCommentServiceImpl implements AdminCommentService {
    /**
     * Поле зависимость от репозитория CommentRepository
     */
    private final CommentRepository commentRepository;

    /**
     * Метод - Публикация комментария
     *
     * @param idComm - id комментария
     */
    public Comment publishComment(long idComm) {
        commentRepository.updateStatus(idComm, StateComment.PUBLISH);
        return commentRepository.findById(idComm).orElse(new Comment());
    }

    /**
     * Метод - отклонение комментария
     *
     * @param idComm - id комментария
     */
    public Comment rejectComment(long idComm) {
        commentRepository.updateStatus(idComm, StateComment.REJECTED);
        return commentRepository.findById(idComm).orElse(new Comment());
    }

}
