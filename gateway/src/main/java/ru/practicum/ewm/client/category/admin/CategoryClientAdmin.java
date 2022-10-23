package ru.practicum.ewm.client.category.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.ewm.client.base.BaseClient;
import ru.practicum.ewm.dtos.category.CategoryDto;

/**
 * Класс клиент категорий (для администратора).
 *
 * @version 1.0
 * @autor Lobachev
 */
@Service
public class CategoryClientAdmin extends BaseClient {
    /**
     * Поле константа URL
     */
    private static final String API_PREFIX = "";

    /**
     * Конструктор - создание нового объекта
     *
     * @see CategoryClientAdmin (String, RestTemplateBuilder )
     */
    @Autowired
    public CategoryClientAdmin(@Value("${ewm-main-service-spec.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        )
        ;
    }

    /**
     * Метод - обновления категории
     *
     * @param categoryDto - сущность (категория)
     */
    public ResponseEntity<Object> updateCategory(CategoryDto categoryDto) {
        return patch("/admin/categories", categoryDto);
    }

    /**
     * Метод - создания категории
     *
     * @param categoryDto - сущность (категория)
     */
    public ResponseEntity<Object> createCategory(CategoryDto categoryDto) {
        return post("/admin/categories", categoryDto);
    }

    /**
     * Метод - удаления категории
     *
     * @param catId - id категории которую необходимо удалить
     */
    public ResponseEntity<Object> deleteCategory(long catId) {
        return delete("/admin/categories/" + catId);
    }
}
