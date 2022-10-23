package ru.practicum.ewm.services.comment.publish;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.exceptions.ExclusionInvalidRequest;
import ru.practicum.ewm.models.comment.Comment;
import ru.practicum.ewm.models.comment.StateComment;
import ru.practicum.ewm.models.event.Event;
import ru.practicum.ewm.models.user.User;
import ru.practicum.ewm.pageable.OffsetLimitPageable;
import ru.practicum.ewm.repositories.comment.CommentRepository;
import ru.practicum.ewm.repositories.event.EventRepository;
import ru.practicum.ewm.repositories.user.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс бизнес логики комментариев (публичный).
 *
 * @version 1.0
 * @autor Lobachev
 */
@RequiredArgsConstructor
@Transactional
@Service
public class PublicCommentServiceImpl implements PublicCommentService {
    /**
     * Поле зависимость от репозитория CommentRepository
     */
    private final CommentRepository commentRepository;
    /**
     * Поле зависимость от репозитория EventRepository
     */
    private final EventRepository eventRepository;
    /**
     * Поле зависимость от репозитория UserRepository
     */
    private final UserRepository userRepository;

    /**
     * Метод - Создания комментария
     *
     * @param comment - тело комментария
     */
    public Comment createComment(Comment comment, long idUser, long idEvent) {
        comment.setEvent(eventRepository.findById(idEvent).orElse(new Event()));
        comment.setOwner(userRepository.findById(idUser).orElse(new User()));
        return commentRepository.save(comment);
    }

    /**
     * Метод - Обновления комментария
     *
     * @param comment - тело комментария
     */
    public Comment updateComment(Comment comment, long idComm, long idUser) {
        checkUserComment(idComm, idUser, "Только автор комментария может его изменять");
        commentRepository.updateComment(comment.getText(), idComm);
        return getCommentById(idComm);
    }

    /**
     * Метод - Получения комментариев (только ОПУБЛИКОВАННЫХ)
     *
     * @param from - с какого комментария вывести
     * @param size - количество комментариев
     */
    public List<Comment> getComments(Integer from, Integer size) {
        Pageable pageable = OffsetLimitPageable.of(from, size, Sort.by("id"));
        List<Comment> comments = commentRepository.findAllByState(StateComment.PUBLISH, pageable).stream().collect(Collectors.toList());
        if (comments.size() == 0) {
            throw new NullPointerException("Комментариев пока еще никто не оставлял");
        }
        return comments;
    }

    /**
     * Метод - Удаление комментария
     *
     * @param idComm - id комментария который необходимо удалить
     */
    public void deleteComment(long idComm, long idUser) {
        checkUserComment(idComm, idUser, "Только автор комментария может его удалять");
        commentRepository.deleteById(idComm);
    }


    /**
     * Метод - приватный метод получения комментария (для проверки на существование комментария)
     *
     * @param idComm - id комментария который нужно обновить
     */
    private Comment getCommentById(long idComm) {
        Comment comment = commentRepository.findById(idComm).orElse(new Comment());
        if (comment.getId() == 0) {
            throw new NullPointerException("Комментарий не найден");
        }
        return comment;
    }

    /**
     * Метод - приватный метод проверки владельца комментария
     *
     * @param idComm - id комментария который нужно обновить
     * @param idUser - id пользователя
     * @param text   - текст ошибки
     */
    private void checkUserComment(long idComm, long idUser, String text) {
        Comment comment = getCommentById(idComm);
        if (comment.getOwner().getId() != idUser) {
            throw new ExclusionInvalidRequest(text);
        }
    }

}
