package ru.practicum.ewm.compilations.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.compilations.dto.CompilationDto;
import ru.practicum.ewm.compilations.dto.CompilationDtoEvent;
import ru.practicum.ewm.compilations.service.CompilationServiceImpl;

import static ru.practicum.ewm.compilations.dto.CompilationMapper.compilationDtoEvent;

@RestController
@RequestMapping(path = "/admin/compilations")
@RequiredArgsConstructor
public class CompilationControllerAdmin {
    private final CompilationServiceImpl compilationService;

    @PostMapping
    public CompilationDtoEvent createCompilation(@RequestBody CompilationDto compilationDto) {
        return compilationDtoEvent(compilationService.createCompilation(compilationDto));
    }

    @DeleteMapping(value = "{compId}")
    public void deleteCompilation(@PathVariable long compId) {
        compilationService.deleteCompilation(compId);
    }

    @DeleteMapping(value = "{compId}/events/{eventId}")
    public void deleteEventFromCompilation(@PathVariable long compId, @PathVariable long eventId) {
        compilationService.deleteEventFromCompilation(compId, eventId);
    }

    @PatchMapping(value = "{compId}/events/{eventId}")
    public void addEventToCompilation(@PathVariable long compId, @PathVariable long eventId) {
        compilationService.addEventToCompilation(compId, eventId);
    }

    @DeleteMapping(value = "{compId}/pin")
    public void unpinCompilation(@PathVariable long compId) {
        compilationService.unpinCompilation(compId);

    }

    @PatchMapping(value = "{compId}/pin")
    public void pinCompilation(@PathVariable long compId) {
        compilationService.pinCompilation(compId);
    }
}
