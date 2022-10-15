package ru.practicum.ewm.categories.service;

import ru.practicum.ewm.categories.model.Category;

import java.util.List;

public interface CategoryService {
    void updateCategory(Category category);

    Category createCategory(Category category);

    void deleteCategory(long catId);

    List<Category> getAllCategories(Integer from, Integer size);

    Category getCategoryById(long catId);
}
