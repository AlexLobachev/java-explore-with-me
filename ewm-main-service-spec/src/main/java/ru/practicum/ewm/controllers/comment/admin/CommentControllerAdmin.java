package ru.practicum.ewm.controllers.comment.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.ewm.dtos.comment.CommentDtoAdmin;
import ru.practicum.ewm.services.comment.admin.AdminCommentServiceImpl;

import static ru.practicum.ewm.mappers.comment.CommentMapper.commentDtoAdmin;

/**
 * Класс контроллер комментариев (для Администратора).
 * ВХОДНАЯ ВАЛИДАЦИЯ - GATEWAY
 *
 * @version 1.0
 * @autor Lobachev
 */
@RestController
@RequestMapping("/events/admin")
@RequiredArgsConstructor
public class CommentControllerAdmin {
    /**
     * Поле зависимость от репозитория PublicCommentServiceImpl
     */

    private final AdminCommentServiceImpl commentService;

    /**
     * Метод - Публикация комментария
     * Эндпоинт - /events/admin/id/publish
     *
     * @param idComm - id комментария
     */
    @PatchMapping(path = "{idComm}/publish")
    public CommentDtoAdmin publishComment(@PathVariable long idComm) {
        return commentDtoAdmin(commentService.publishComment(idComm));
    }

    /**
     * Метод - Публикация комментария
     * Эндпоинт - /events/admin/id/reject
     *
     * @param idComm - id комментария
     */
    @PatchMapping(path = "{idComm}/reject")
    public CommentDtoAdmin rejectComment(@PathVariable long idComm) {
        return commentDtoAdmin(commentService.rejectComment(idComm));
    }


}
