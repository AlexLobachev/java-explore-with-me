package ru.practicum.ewm.compilations.service;

import org.springframework.stereotype.Repository;
import ru.practicum.ewm.compilations.dto.CompilationDto;
import ru.practicum.ewm.compilations.model.Compilation;

import java.util.List;

@Repository
public interface CompilationService {
    Compilation createCompilation(CompilationDto compilationDto);

    void deleteCompilation(long compId);

    void deleteEventFromCompilation(long compId, long eventId);

    void addEventToCompilation(long compId, long eventId);

    void unpinCompilation(long compId);

    void pinCompilation(long compId);

    List<Compilation> getAllCompilation(Boolean pinned, Integer from, Integer size);

    Compilation getCompilationById(long compId);
}
