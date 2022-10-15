package ru.practicum.ewm.events.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import ru.practicum.ewm.categories.model.Category;

import java.time.LocalDateTime;

@Getter
@Setter
public class UpdateEventRequest {
    private String annotation;
    private Category category;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;
    private long id;
    private Boolean paid;
    private String title;
}
