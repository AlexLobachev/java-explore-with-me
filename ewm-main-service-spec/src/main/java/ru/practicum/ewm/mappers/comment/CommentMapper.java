package ru.practicum.ewm.mappers.comment;

import ru.practicum.ewm.dtos.comment.CommentDto;
import ru.practicum.ewm.dtos.comment.CommentDtoAdmin;
import ru.practicum.ewm.models.comment.Comment;

/**
 * Класс маппер ДТО комментариев
 *
 * @version 1.0
 * @autor Lobachev
 */
public class CommentMapper {
    /**
     * Метод - конвертировать Comment -> CommentDto
     *
     * @param comment - объект Comment
     */
    public static CommentDto commentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setDateComment(comment.getDateComment());
        commentDto.setEvent(comment.getEvent().getId());
        commentDto.setOwner(comment.getOwner().getId());
        commentDto.setText(comment.getText());
        return commentDto;

    }

    /**
     * Метод - конвертировать Comment -> CommentDtoAdmin
     *
     * @param comment - объект Comment
     */
    public static CommentDtoAdmin commentDtoAdmin(Comment comment) {
        CommentDtoAdmin commentDtoAdmin = new CommentDtoAdmin();
        commentDtoAdmin.setState(comment.getState());
        commentDtoAdmin.setEvent(comment.getEvent().getId());
        commentDtoAdmin.setOwner(comment.getOwner().getId());
        commentDtoAdmin.setText(comment.getText());
        return commentDtoAdmin;

    }

}
