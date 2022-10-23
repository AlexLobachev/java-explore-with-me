package ru.practicum.ewm.mappers.compilation;


import ru.practicum.ewm.dtos.compilation.CompilationDto;
import ru.practicum.ewm.dtos.compilation.CompilationDtoEvent;
import ru.practicum.ewm.mappers.event.EventMapper;
import ru.practicum.ewm.models.compilation.Compilation;
import ru.practicum.ewm.models.event.Event;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс маппер ДТО подборок событий
 *
 * @version 1.0
 * @autor Lobachev
 */
public class CompilationMapper {
    /**
     * Метод - конвертировать compilationDto -> Compilation
     *
     * @param compilationDto - объект CompilationDto
     * @param events         - коллекция событий
     */
    public static Compilation compilationDtoToCompilation(CompilationDto compilationDto, List<Event> events) {
        Compilation compilation = new Compilation();
        compilation.setEvents(events);
        compilation.setPinned(compilationDto.getPinned());
        compilation.setTitle(compilationDto.getTitle());
        System.out.println(compilation);
        return compilation;

    }

    /**
     * Метод - конвертировать Compilation -> CompilationDtoEvent
     *
     * @param compilation - объект Compilation
     */
    public static CompilationDtoEvent compilationDtoEvent(Compilation compilation) {
        CompilationDtoEvent compilationDtoEvent = new CompilationDtoEvent();
        compilationDtoEvent.setEvents(compilation.getEvents().stream().map(EventMapper::eventShortDto).collect(Collectors.toList()));
        compilationDtoEvent.setPinned(compilation.getPinned());
        compilationDtoEvent.setTitle(compilation.getTitle());
        compilationDtoEvent.setId(compilation.getId());
        return compilationDtoEvent;

    }
}
