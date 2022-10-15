package ru.practicum.ewm.categories.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.categories.CategoryClient;
import ru.practicum.ewm.categories.dto.CategoryDto;

import javax.validation.Valid;


@Controller
@RequestMapping(path = "/admin/categories")
@AllArgsConstructor
@Valid
public class CategoryControllerAdmin {
    private final CategoryClient categoryClient;

    @PatchMapping
    public ResponseEntity<Object> updateCategory(@RequestBody CategoryDto categoryDto) {
        return categoryClient.updateCategory(categoryDto);
    }

    @PostMapping
    public ResponseEntity<Object> createCategory(@RequestBody CategoryDto categoryDto) {
        return categoryClient.createCategory(categoryDto);
    }

    @DeleteMapping(path = "{catId}")
    public ResponseEntity<Object> deleteCategory(@PathVariable long catId) {
        return categoryClient.deleteCategory(catId);
    }


}
