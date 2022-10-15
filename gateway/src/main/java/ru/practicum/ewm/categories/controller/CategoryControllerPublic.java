package ru.practicum.ewm.categories.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.practicum.ewm.categories.CategoryClient;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Controller
@RequestMapping(path = "/categories")
@AllArgsConstructor
@Valid
public class CategoryControllerPublic {
    private final CategoryClient categoryClient;

    @GetMapping
    public ResponseEntity<Object> getAllCategories(@PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                   @Positive @RequestParam(defaultValue = "20") Integer size) {
        return categoryClient.getAllCategories(from, size);
    }

    @GetMapping(path = "{catId}")
    public ResponseEntity<Object> getCategoryById(@PathVariable long catId) {
        return categoryClient.getCategoryById(catId);
    }


}
