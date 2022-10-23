package ru.practicum.ewm.models.category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

/**
 * Класс сущность - категорий со свойствами: <b>id</b> и <b>name</b>.
 *
 * @version 2.1
 * @autor Киса Воробьянинов
 */
@Getter
@Setter
@Entity
@ToString
@Table(name = "categories")
@NoArgsConstructor
public class Category {
    /**
     * Поле идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    /**
     * Поле имя категории
     */
    @Column(name = "name")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id == category.id && Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
