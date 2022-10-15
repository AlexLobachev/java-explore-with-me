package ru.practicum.ewm.categories.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.OffsetLimitPageable;
import ru.practicum.ewm.categories.CategoryRepository;
import ru.practicum.ewm.categories.model.Category;
import ru.practicum.ewm.exception.ExceptionNotFound;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    /**
     * ADMIN  CATEGORY
     */
    public void updateCategory(Category category) {
        getCategoryById(category.getId());
        categoryRepository.updateCategory(category.getName(), category.getId());
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteCategory(long catId) {
        getCategoryById(catId);
        categoryRepository.deleteById(catId);
    }

    /**
     * PUBLIC  CATEGORY
     */
    public List<Category> getAllCategories(Integer from, Integer size) {
        Pageable pageable = OffsetLimitPageable.of(from, size, Sort.by("id"));
        return categoryRepository.findAll(pageable).stream().collect(Collectors.toList());
    }

    public Category getCategoryById(long catId) {
        Category category = categoryRepository.findById(catId).orElse(new Category());

        if (category.getId() != catId) {
            throw new ExceptionNotFound("Категория не найдена");
        }
        return category;
    }


}
