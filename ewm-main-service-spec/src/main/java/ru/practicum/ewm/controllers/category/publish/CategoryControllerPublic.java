package ru.practicum.ewm.controllers.category.publish;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.models.category.Category;
import ru.practicum.ewm.services.category.publish.PublicCategoryServiceImpl;

import java.util.List;

/**
 * Класс контроллер категорий событий (Публичный).
 *
 * @version 1.0
 * @autor Lobachev
 */
@RestController
@RequestMapping(path = "/categories")
@RequiredArgsConstructor
public class CategoryControllerPublic {
    /**
     * Поле зависимсость от сервисного класса CategoryServiceImpl
     */
    private final PublicCategoryServiceImpl categoryService;

    /**
     * Метод - получения списка событий
     * Эндпоинт - /categories
     *
     * @param from - с какого события начать поиск
     * @param size - ограничение кличества выборки событий
     */
    @GetMapping
    public List<Category> getAllCategories(@RequestParam(defaultValue = "0") Integer from,
                                           @RequestParam(defaultValue = "20") Integer size) {
        return categoryService.getAllCategories(from, size);
    }

    /**
     * Метод - получения событий по id
     * Энпоинт - /categories
     *
     * @param catId - id нужного события
     */
    @GetMapping(path = "{catId}")
    public Category getCategoryById(@PathVariable long catId) {
        return categoryService.getCategoryById(catId);
    }


}
