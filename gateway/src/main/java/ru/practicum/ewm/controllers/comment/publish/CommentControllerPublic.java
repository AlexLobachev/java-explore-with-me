package ru.practicum.ewm.controllers.comment.publish;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.client.comment.publish.CommentClientPublic;
import ru.practicum.ewm.dtos.comment.CommentDto;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

/**
 * Класс контроллер комментариев (Публичный).
 *
 * @version 1.0
 * @autor Lobachev
 */
@Controller
@RequestMapping(path = "/events")
@AllArgsConstructor
@Valid
public class CommentControllerPublic {
    /**
     * Поле зависимость от клиента CommentClientAdmin
     */

    private final CommentClientPublic commentClientAdmin;

    /**
     * Метод - Создания комментария
     * Эндпоинт - /events/id/comment
     *
     * @param commentDto - тело комментария
     */
    @PostMapping(path = "{idEvent}/comment")
    public ResponseEntity<Object> createComment(@Valid @RequestBody CommentDto commentDto,
                                                @RequestHeader("X-Sharer-User-Id") long idUser,
                                                @PathVariable long idEvent) {

        return commentClientAdmin.createComment(commentDto, idUser, idEvent);
    }

    /**
     * Метод - Обновления комментария
     * Эндпоинт - /events/id/comment
     *
     * @param commentDto - тело комментария
     */
    @PatchMapping(path = "{idComm}/comment")
    public ResponseEntity<Object> updateComment(@Valid @RequestBody CommentDto commentDto,
                                                @PathVariable long idComm,
                                                @RequestHeader("X-Sharer-User-Id") long idUser) {
        return commentClientAdmin.updateComment(commentDto, idComm, idUser);
    }

    /**
     * Метод - Получения комментариев
     * Эндпоинт - events/comments
     *
     * @param from - с какого комментария вывести
     * @param size - количество комментариев
     */
    @GetMapping(path = "/comments")
    public ResponseEntity<Object> getComments(@Positive @RequestParam(defaultValue = "0") Integer from,
                                              @PositiveOrZero @RequestParam(defaultValue = "20") Integer size) {

        return commentClientAdmin.getComments(from, size);
    }

    /**
     * Метод - Удаление комментария
     * Эндпоинт - events/id/comment
     *
     * @param idComm - id комментария который необходимо удалить
     */
    @DeleteMapping(path = "{idComm}")
    public ResponseEntity<Object> deleteComment(@PathVariable long idComm, @RequestHeader("X-Sharer-User-Id") long idUser) {
        return commentClientAdmin.deleteComment(idComm, idUser);
    }
}
