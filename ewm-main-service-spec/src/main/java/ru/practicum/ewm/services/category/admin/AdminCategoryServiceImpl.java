package ru.practicum.ewm.services.category.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.models.category.Category;
import ru.practicum.ewm.repositories.category.CategoryRepository;
import ru.practicum.ewm.services.category.publish.PublicCategoryServiceImpl;

/**
 * Класс бизнес логики категорий (для администратора).
 *
 * @version 1.0
 * @autor Lobachev
 */
@Service
@RequiredArgsConstructor
@Transactional
public class AdminCategoryServiceImpl implements AdminCategoryService {
    /**
     * Поле зависимость от репозитория CategoryRepository
     */
    private final CategoryRepository categoryRepository;
    private final PublicCategoryServiceImpl categoryService;

    /**
     * Метод - Изменение категории
     *
     * @param category - данные категории
     * @return Category
     */
    public Category updateCategory(Category category) {
        categoryRepository.updateCategory(category.getName(), category.getId());
        return categoryService.getCategoryById(category.getId());
    }

    /**
     * Метод - Добавление новой категории
     *
     * @param category - данные категории
     */
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    /**
     * Метод - Удаление категории
     *
     * @param catId - id категории
     */
    public void deleteCategory(long catId) {

        categoryRepository.deleteById(catId);
    }
}
