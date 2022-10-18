package ru.practicum.ewm.controllers.category.admin;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.client.category.admin.CategoryClientAdmin;
import ru.practicum.ewm.dtos.category.CategoryDto;

import javax.validation.Valid;

/**
 * Класс контроллер категорий событий. (для Администратора)
 *
 * @version 1.0
 * @autor Lobachev
 */
@Controller
@RequestMapping(path = "/admin/categories")
@AllArgsConstructor
@Valid
public class CategoryControllerAdmin {
    /**
     * Поле зависимсость от сервисного класса CategoryClient
     */
    private final CategoryClientAdmin categoryClient;

    /**
     * Метод - обновления категории
     * Энпоинт - /admin/categories
     *
     * @param categoryDto - сущность (категория)
     */
    @PatchMapping
    public ResponseEntity<Object> updateCategory(@Validated @RequestBody CategoryDto categoryDto) {
        return categoryClient.updateCategory(categoryDto);
    }

    /**
     * Метод - создания категории
     * Энпоинт - /admin/categories
     *
     * @param categoryDto - сущность (категория)
     */
    @PostMapping
    public ResponseEntity<Object> createCategory(@Validated @RequestBody CategoryDto categoryDto) {
        return categoryClient.createCategory(categoryDto);
    }

    /**
     * Метод - удаления категории
     * Энпоинт - /admin/categories/id
     *
     * @param catId - id категории которую необходимо удалить
     */
    @DeleteMapping(path = "{catId}")
    public ResponseEntity<Object> deleteCategory(@PathVariable long catId) {
        return categoryClient.deleteCategory(catId);
    }


}
