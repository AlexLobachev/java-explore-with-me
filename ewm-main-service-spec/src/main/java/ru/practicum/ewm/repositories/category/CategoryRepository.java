package ru.practicum.ewm.repositories.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.ewm.models.category.Category;

/**
 * Интерфейс репозиторий категорий.
 *
 * @version 1.0
 * @autor Lobachev
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    /**
     * Метод - обновления категории
     *
     * @param name - id категории
     * @param name - имя категории
     */
    @Modifying(clearAutomatically = true)
    @Query("update Category c set c.name =?1 where c.id = ?2")
    void updateCategory(String name, long id);


}
