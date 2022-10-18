package ru.practicum.ewm.models.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

/**
 * Класс сущность - пользователя со свойствами: <b>id</b>, <b>name</b>, <b>email</b>
 *
 * @version 2.1
 * @autor Lobachev
 */
@Getter
@Setter
@Entity
@ToString
@Table(name = "users")
@NoArgsConstructor
public class User {
    /**
     * Поле идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    /**
     * Поле имя
     */
    private String name;
    /**
     * Поле email
     */
    @Column(name = "email")
    private String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email);
    }
}
