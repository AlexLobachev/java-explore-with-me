package ru.practicum.ewm.models.comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.practicum.ewm.models.event.Event;
import ru.practicum.ewm.models.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Класс сущность - комментариев со свойствами: <b>id</b> и <b>name</b>.
 *
 * @version 2.1
 * @autor Lobachev
 */
@Getter
@Setter
@Entity
@ToString
@Table(name = "comments")
@NoArgsConstructor
public class Comment {
    /**
     * Поле идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    /**
     * Поле комментарий (так же модерируется админом на цензуру)
     */
    @Column(name = "text_comment")
    private String text;
    /**
     * Поле пользователь оставивший комментарий
     */
    @ManyToOne
    private User owner;
    /**
     * Поле событие
     */
    @ManyToOne
    private Event event;
    /**
     * Поле дата комментария
     */
    private LocalDateTime dateComment = LocalDateTime.now();
    /**
     * Поле список состояний жизненного цикла комментария
     */
    @Enumerated(EnumType.STRING)
    private StateComment state = StateComment.PENDING;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id == comment.id && Objects.equals(text, comment.text) && Objects.equals(owner, comment.owner) && Objects.equals(event, comment.event) && Objects.equals(dateComment, comment.dateComment) && state == comment.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, owner, event, dateComment, state);
    }
}
