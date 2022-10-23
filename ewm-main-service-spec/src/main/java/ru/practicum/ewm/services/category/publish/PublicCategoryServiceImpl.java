package ru.practicum.ewm.services.category.publish;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.models.category.Category;
import ru.practicum.ewm.pageable.OffsetLimitPageable;
import ru.practicum.ewm.repositories.category.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс бизнес логики категорий (публичный).
 *
 * @version 1.0
 * @autor Lobachev
 */
@Service
@RequiredArgsConstructor
@Transactional
public class PublicCategoryServiceImpl implements PublicCategoryService {
    /**
     * Поле зависимость от репозитория CategoryRepository
     */
    private final CategoryRepository categoryRepository;

    /**
     * Метод - Получение категорий
     *
     * @param from - с какого место необходимо искать подборку
     * @param size - количество для вывода
     */
    public List<Category> getAllCategories(Integer from, Integer size) {
        Pageable pageable = OffsetLimitPageable.of(from, size, Sort.by("id"));
        return categoryRepository.findAll(pageable).stream().collect(Collectors.toList());
    }

    /**
     * Метод - Получение категорий (Категория не должна быть найдена после удаления | AssertionError: expected { error: 'Категория не найдена' } to be null)
     *
     * @param catId - id категории
     */
    public Category getCategoryById(long catId) {
        Category category = categoryRepository.findById(catId).orElse(new Category());

        if (category.getId() != catId) {
            throw new NullPointerException("Категория не найдена");
        }
        return category;
    }
}
