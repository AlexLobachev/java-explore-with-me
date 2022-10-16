package ru.practicum.ewm.compilations.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class CompilationDto {
    private List<Long> events;
    private Boolean pinned;
    @NotBlank
    private String title;
}