package ru.practicum.ewm.compilations.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.compilations.CompilationClient;
import ru.practicum.ewm.compilations.dto.CompilationDto;

import javax.validation.Valid;


@Controller
@RequestMapping(path = "/admin/compilations")
@AllArgsConstructor
@Valid
public class CompilationControllerAdmin {
    private final CompilationClient compilationClient;

    @PostMapping
    public ResponseEntity<Object> createCompilation(@Validated @RequestBody CompilationDto compilationDto) {
        return compilationClient.createCompilation(compilationDto);
    }

    @DeleteMapping(value = "{compId}")
    public ResponseEntity<Object> deleteCompilation(@PathVariable long compId) {
        return compilationClient.deleteCompilation(compId);
    }

    @DeleteMapping(value = "{compId}/events/{eventId}")
    public ResponseEntity<Object> deleteEventFromCompilation(@PathVariable long compId, @PathVariable long eventId) {
        return compilationClient.deleteEventFromCompilation(compId, eventId);
    }

    @PatchMapping(value = "{compId}/events/{eventId}")
    public ResponseEntity<Object> addEventToCompilation(@PathVariable long compId, @PathVariable long eventId) {
        return compilationClient.addEventToCompilation(compId, eventId);
    }

    @DeleteMapping(value = "{compId}/pin")
    public ResponseEntity<Object> unpinCompilation(@PathVariable long compId) {
        return compilationClient.unpinCompilation(compId);

    }

    @PatchMapping(value = "{compId}/pin")
    public ResponseEntity<Object> pinCompilation(@PathVariable long compId) {
        return compilationClient.pinCompilation(compId);
    }
}
