package ru.practicum.ewm.events.dto;

import lombok.Getter;
import lombok.Setter;
import ru.practicum.ewm.categories.model.Category;
import ru.practicum.ewm.users.dto.UserShortDto;

import java.time.LocalDateTime;

@Getter
@Setter
public class EventShortDto {
    private String annotation;
    private Category category;
    private Integer confirmedRequests;
    private LocalDateTime eventDate;
    private long id;
    private UserShortDto initiator;
    private Boolean paid;
    private String title;
    private long views;

}
