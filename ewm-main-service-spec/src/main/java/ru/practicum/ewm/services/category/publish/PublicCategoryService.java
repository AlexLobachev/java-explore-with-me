package ru.practicum.ewm.services.category.publish;

import ru.practicum.ewm.models.category.Category;

import java.util.List;

/**
 * Интерфейс категорий (публичный).
 *
 * @version 1.0
 * @autor Lobachev
 */
public interface PublicCategoryService {
    /**
     * Метод - Получение категорий
     *
     * @param from - с какого место необходимо искать подборку
     * @param size - количество для вывода
     */
    List<Category> getAllCategories(Integer from, Integer size);

    /**
     * Метод - Получение категорий
     *
     * @param catId - id категории
     */
    Category getCategoryById(long catId);
}
