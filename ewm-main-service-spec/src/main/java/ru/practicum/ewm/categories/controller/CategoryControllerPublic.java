package ru.practicum.ewm.categories.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.categories.model.Category;
import ru.practicum.ewm.categories.service.CategoryServiceImpl;

import java.util.List;

@RestController
@RequestMapping(path = "/categories")
@RequiredArgsConstructor
public class CategoryControllerPublic {
    private final CategoryServiceImpl categoryService;

    @GetMapping
    public List<Category> getAllCategories(@RequestParam(defaultValue = "0") Integer from,
                                           @RequestParam(defaultValue = "20") Integer size) {
        return categoryService.getAllCategories(from, size);
    }

    @GetMapping(path = "{catId}")
    public Category getCategoryById(@PathVariable long catId) {
        return categoryService.getCategoryById(catId);
    }


}
