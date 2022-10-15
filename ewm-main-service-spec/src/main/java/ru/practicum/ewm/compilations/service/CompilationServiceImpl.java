package ru.practicum.ewm.compilations.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.OffsetLimitPageable;
import ru.practicum.ewm.compilations.CompilationRepository;
import ru.practicum.ewm.compilations.dto.CompilationDto;
import ru.practicum.ewm.compilations.model.Compilation;
import ru.practicum.ewm.events.EventRepository;
import ru.practicum.ewm.exception.ExceptionNotFound;

import java.util.List;
import java.util.stream.Collectors;

import static ru.practicum.ewm.compilations.dto.CompilationMapper.compilationDtoToCompilation;

@Service
@RequiredArgsConstructor
public class CompilationServiceImpl implements CompilationService {
    private final CompilationRepository compilationRepository;
    private final EventRepository eventRepository;

    public Compilation createCompilation(CompilationDto compilationDto) {

        Compilation compilation = compilationDtoToCompilation(compilationDto, eventRepository.findAllById(compilationDto.getEvents()));
        return compilationRepository.save(compilation);
    }

    public void deleteCompilation(long compId) {
        getCompilationById(compId);
        compilationRepository.deleteById(compId);
    }

    public void deleteEventFromCompilation(long compId, long eventId) {
        getCompilationById(compId);
        compilationRepository.deleteCompilation(compId, eventId);
    }

    public void addEventToCompilation(long compId, long eventId) {
        getCompilationById(compId);
        compilationRepository.addEvent(compId, eventId);
    }

    public void unpinCompilation(long compId) {
        getCompilationById(compId);
        compilationRepository.updatePin(false, compId);
    }

    public void pinCompilation(long compId) {
        getCompilationById(compId);
        compilationRepository.updatePin(true, compId);
    }

    public List<Compilation> getAllCompilation(Boolean pinned, Integer from, Integer size) {
        Pageable pageable = OffsetLimitPageable.of(from, size, Sort.by("id"));
        List<Compilation> compilations = compilationRepository.findAllByPinned(pinned, pageable).stream().collect(Collectors.toList());
        if (compilations.size() == 0) {
            throw new ExceptionNotFound("Список подборок пуст");
        }
        return compilations;
    }

    public Compilation getCompilationById(long compId) {
        Compilation compilation = compilationRepository.findById(compId).orElse(new Compilation());
        if (compilation.getId() == 0) {
            throw new ExceptionNotFound("Подборка не найдена");
        }
        return compilation;
    }

}
