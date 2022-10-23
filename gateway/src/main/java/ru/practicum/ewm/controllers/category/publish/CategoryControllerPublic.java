package ru.practicum.ewm.controllers.category.publish;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.practicum.ewm.client.category.publish.CategoryClientPublic;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

/**
 * Класс контроллер категорий событий (Публичный).
 *
 * @version 1.0
 * @autor Lobachev
 */
@Controller
@RequestMapping(path = "/categories")
@AllArgsConstructor
@Valid
public class CategoryControllerPublic {
    /**
     * Поле зависимсость от сервисного класса CategoryClient
     */
    private final CategoryClientPublic categoryClient;

    /**
     * Метод - получения списка событий
     * Эндпоинт - /categories
     *
     * @param from - с какого события начать поиск
     * @param size - ограничение кличества выборки событий
     */
    @GetMapping
    public ResponseEntity<Object> getAllCategories(@PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                   @Positive @RequestParam(defaultValue = "20") Integer size) {
        return categoryClient.getAllCategories(from, size);
    }

    /**
     * Метод - получения событий по id
     * Энпоинт - /categories
     *
     * @param catId - id нужного события
     */
    @GetMapping(path = "{catId}")
    public ResponseEntity<Object> getCategoryById(@PathVariable long catId) {
        return categoryClient.getCategoryById(catId);
    }


}
