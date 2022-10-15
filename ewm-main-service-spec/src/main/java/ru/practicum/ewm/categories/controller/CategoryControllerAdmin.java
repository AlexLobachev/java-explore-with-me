package ru.practicum.ewm.categories.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.categories.model.Category;
import ru.practicum.ewm.categories.service.CategoryServiceImpl;

@RestController
@RequestMapping(path = "/admin/categories")
@AllArgsConstructor
public class CategoryControllerAdmin {
    private final CategoryServiceImpl categoryService;

    @PatchMapping
    public void updateCategory(@RequestBody Category category) {
        categoryService.updateCategory(category);
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @DeleteMapping(path = "{catId}")
    public void deleteCategory(@PathVariable long catId) {
        categoryService.deleteCategory(catId);
    }


}
