package ru.practicum.ewm.controllers.comment.publish;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dtos.comment.CommentDto;
import ru.practicum.ewm.mappers.comment.CommentMapper;
import ru.practicum.ewm.models.comment.Comment;
import ru.practicum.ewm.services.comment.publish.PublicCommentServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import static ru.practicum.ewm.mappers.comment.CommentMapper.commentDto;

/**
 * Класс контроллер комментариев (Публичный).
 * ВХОДНАЯ ВАЛИДАЦИЯ - GATEWAY
 *
 * @version 1.0
 * @autor Lobachev
 */
@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class CommentControllerPublic {
    /**
     * Поле зависимость от репозитория PublicCommentServiceImpl
     */

    private final PublicCommentServiceImpl commentService;

    /**
     * Метод - Создания комментария
     * Эндпоинт - /events/id/comment
     *
     * @param comment - тело комментария
     */
    @PostMapping(path = "{idEvent}/comment")
    public Comment createComment(@RequestBody Comment comment,
                                 @RequestHeader("X-Sharer-User-Id") long idUser,
                                 @PathVariable long idEvent) {
        return commentService.createComment(comment, idUser, idEvent);
    }

    /**
     * Метод - Обновления комментария
     * Эндпоинт - /events/id/comment
     *
     * @param comment - тело комментария
     */
    @PatchMapping(path = "{idComm}/comment")
    public CommentDto updateComment(@RequestBody Comment comment,
                                    @PathVariable long idComm,
                                    @RequestHeader("X-Sharer-User-Id") long idUser) {
        return commentDto(commentService.updateComment(comment, idComm, idUser));
    }

    /**
     * Метод - Получения комментариев
     * Эндпоинт - events/comments
     *
     * @param from - с какого комментария вывести
     * @param size - количество комментариев
     */
    @GetMapping(path = "/comments")
    public List<CommentDto> getComments(@RequestParam(defaultValue = "0") Integer from,
                                        @RequestParam(defaultValue = "20") Integer size) {

        return commentService.getComments(from, size).stream().map(CommentMapper::commentDto).collect(Collectors.toList());
    }

    /**
     * Метод - Удаление комментария
     * Эндпоинт - events/id/comment
     *
     * @param idComm - id комментария который необходимо удалить
     */
    @DeleteMapping(path = "{idComm}")
    public void deleteComment(@PathVariable long idComm, @RequestHeader("X-Sharer-User-Id") long idUser) {
        commentService.deleteComment(idComm, idUser);
    }


}
