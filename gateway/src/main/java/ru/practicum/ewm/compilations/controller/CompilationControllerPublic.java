package ru.practicum.ewm.compilations.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.compilations.CompilationClient;

import javax.validation.constraints.PositiveOrZero;


@RestController
@RequestMapping(path = "/compilations")
@RequiredArgsConstructor
public class CompilationControllerPublic {
    private final CompilationClient compilationClient;

    @GetMapping
    public ResponseEntity<Object> getAllCompilation(@RequestParam Boolean pinned,
                                                    @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                    @RequestParam Integer size) {
        return compilationClient.getAllCompilation(pinned, from, size);
    }

    @GetMapping(path = "{compId}")
    public ResponseEntity<Object> getCompilationById(@PathVariable long compId) {
        return compilationClient.getCompilationById(compId);
    }


}
