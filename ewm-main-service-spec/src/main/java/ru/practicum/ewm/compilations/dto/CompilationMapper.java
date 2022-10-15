package ru.practicum.ewm.compilations.dto;

import ru.practicum.ewm.compilations.model.Compilation;
import ru.practicum.ewm.events.dto.EventMapper;
import ru.practicum.ewm.events.model.Event;

import java.util.List;
import java.util.stream.Collectors;

public class CompilationMapper {
    public static Compilation compilationDtoToCompilation(CompilationDto compilationDto, List<Event> events) {
        Compilation compilation = new Compilation();
        compilation.setEvents(events);
        compilation.setPinned(compilationDto.getPinned());
        compilation.setTitle(compilationDto.getTitle());
        System.out.println(compilation);
        return compilation;

    }

    public static CompilationDtoEvent compilationDtoEvent(Compilation compilation) {
        CompilationDtoEvent compilationDtoEvent = new CompilationDtoEvent();
        compilationDtoEvent.setEvents(compilation.getEvents().stream().map(EventMapper::eventShortDto).collect(Collectors.toList()));
        compilationDtoEvent.setPinned(compilation.getPinned());
        compilationDtoEvent.setTitle(compilation.getTitle());
        compilationDtoEvent.setId(compilation.getId());
        return compilationDtoEvent;

    }
}
