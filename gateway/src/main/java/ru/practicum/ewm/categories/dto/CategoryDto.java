package ru.practicum.ewm.categories.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class CategoryDto {
    private long id;
    @Size(min = 1)
    private String name;
}
