package ru.practicum.ewm.compilations.dto;

import lombok.Getter;
import lombok.Setter;
import ru.practicum.ewm.events.dto.EventShortDto;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CompilationDtoEvent {
    private List<EventShortDto> events = new ArrayList<>();
    private long id;
    private Boolean pinned;
    private String title;
}
