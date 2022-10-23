package ru.practicum.ewm.services.category.admin;

import ru.practicum.ewm.models.category.Category;

/**
 * Интерфейс категорий (для администратора).
 *
 * @version 1.0
 * @autor Lobachev
 */
public interface AdminCategoryService {
    /**
     * Метод - Изменение категории
     *
     * @param category - данные категории
     * @return
     */
    Category updateCategory(Category category);

    /**
     * Метод - Добавление новой категории
     *
     * @param category - данные категории
     */
    Category createCategory(Category category);

    /**
     * Метод - Удаление категории
     *
     * @param catId - id категории
     */
    void deleteCategory(long catId);
}
