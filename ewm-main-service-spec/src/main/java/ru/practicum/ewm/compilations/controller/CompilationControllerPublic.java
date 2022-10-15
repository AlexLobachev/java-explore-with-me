package ru.practicum.ewm.compilations.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.compilations.dto.CompilationDtoEvent;
import ru.practicum.ewm.compilations.dto.CompilationMapper;
import ru.practicum.ewm.compilations.service.CompilationServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import static ru.practicum.ewm.compilations.dto.CompilationMapper.compilationDtoEvent;

@RestController
@RequestMapping(path = "/compilations")
@RequiredArgsConstructor
public class CompilationControllerPublic {
    private final CompilationServiceImpl compilationService;

    @GetMapping
    public List<CompilationDtoEvent> getAllCompilation(@RequestParam Boolean pinned,
                                                       @RequestParam Integer from,
                                                       @RequestParam Integer size) {
        return compilationService.getAllCompilation(pinned, from, size).stream().map(CompilationMapper::compilationDtoEvent).collect(Collectors.toList());
    }

    @GetMapping(path = "{compId}")
    public CompilationDtoEvent getCompilationById(@PathVariable long compId) {
        return compilationDtoEvent(compilationService.getCompilationById(compId));
    }


}
