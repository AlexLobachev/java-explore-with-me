package ru.practicum.ewm.controllers.comment.admin;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.practicum.ewm.client.comment.admin.CommentClientAdmin;

import javax.validation.Valid;

/**
 * Класс контроллер комментариев (для Администратора).
 *
 * @version 1.0
 * @autor Lobachev
 */
@Controller
@RequestMapping("/events/admin")
@AllArgsConstructor
@Valid
public class CommentControllerAdmin {
    /**
     * Поле зависимость от клиента CommentClientAdmin
     */

    private final CommentClientAdmin commentClientAdmin;

    /**
     * Метод - Публикация комментария
     * Эндпоинт - /events/admin/id/publish
     *
     * @param idComm - id комментария
     */
    @PatchMapping(path = "{idComm}/publish")
    public ResponseEntity<Object> publishComment(@PathVariable long idComm) {
        return commentClientAdmin.publishComment(idComm);
    }

    /**
     * Метод - Публикация комментария
     * Эндпоинт - /events/admin/id/reject
     *
     * @param idComm - id комментария
     */
    @PatchMapping(path = "{idComm}/reject")
    public ResponseEntity<Object> rejectComment(@PathVariable long idComm) {
        return commentClientAdmin.rejectComment(idComm);
    }
}
