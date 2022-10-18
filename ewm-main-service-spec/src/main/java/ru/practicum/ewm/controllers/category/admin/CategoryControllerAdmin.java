package ru.practicum.ewm.controllers.category.admin;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.models.category.Category;
import ru.practicum.ewm.services.category.admin.AdminCategoryService;

/**
 * Класс контроллер категорий событий. (для Администратора)
 *
 * @version 1.0
 * @autor Lobachev
 */
@RestController
@RequestMapping(path = "/admin/categories")
@AllArgsConstructor
public class CategoryControllerAdmin {
    /**
     * Поле зависимсость от сервисного класса CategoryServiceImpl
     */
    private final AdminCategoryService categoryService;

    /**
     * Метод - обновления категории
     * Энпоинт - /admin/categories
     *
     * @param category - сущность (категория)
     */
    @PatchMapping
    public void updateCategory(@RequestBody Category category) {
        categoryService.updateCategory(category);
    }

    /**
     * Метод - создания категории
     * Энпоинт - /admin/categories
     *
     * @param category - сущность (категория)
     */
    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    /**
     * Метод - удаления категории
     * Энпоинт - /admin/categories/id
     *
     * @param catId - id категории которую необходимо удалить
     */
    @DeleteMapping(path = "{catId}")
    public void deleteCategory(@PathVariable long catId) {
        categoryService.deleteCategory(catId);
    }
}
