package ru.practicum.ewm.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.ewm.categories.dto.CategoryDto;
import ru.practicum.ewm.client.BaseClient;

import java.util.Map;

@Service
public class CategoryClient extends BaseClient {
    private static final String API_PREFIX = "";

    @Autowired
    public CategoryClient(@Value("${ewm-main-service-spec.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        )
        ;
    }

    /**
     * ADMIN  CATEGORY
     */
    public ResponseEntity<Object> updateCategory(CategoryDto categoryDto) {
        return patch("/admin/categories", categoryDto);
    }

    public ResponseEntity<Object> createCategory(CategoryDto categoryDto) {
        return post("/admin/categories", categoryDto);
    }

    public ResponseEntity<Object> deleteCategory(long catId) {
        return delete("/admin/categories/" + catId);
    }

    /**
     * PUBLIC  CATEGORY
     */
    public ResponseEntity<Object> getAllCategories(Integer from, Integer size) {
        Map<String, Object> parameters = Map.of(
                "from", from,
                "size", size
        );
        return get("/categories", parameters);
    }

    public ResponseEntity<Object> getCategoryById(long catId) {
        return get("/categories/" + catId);
    }


}
