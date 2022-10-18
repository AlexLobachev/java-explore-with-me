package ru.practicum.ewm.services.compilation.publish;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.exceptions.ExceptionNotFound;
import ru.practicum.ewm.models.compilation.Compilation;
import ru.practicum.ewm.pageable.OffsetLimitPageable;
import ru.practicum.ewm.repositories.compilation.CompilationRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс бизнес логики подборок событий. (публичный)
 *
 * @version 1.0
 * @autor Lobachev
 */
@Service
@RequiredArgsConstructor
public class PublishCompilationServiceImpl implements PublishCompilationService {
    /**
     * Поле зависимость от репозитория CompilationRepository
     */
    private final CompilationRepository compilationRepository;

    /**
     * Метод - получение всех подборок
     *
     * @param pinned - условие поиска (закрепленные/незакрепленные подборки)
     * @param from   - с какого место необходимо искать подборку
     * @param size   - количество для вывода
     */
    public List<Compilation> getAllCompilation(Boolean pinned, Integer from, Integer size) {
        Pageable pageable = OffsetLimitPageable.of(from, size, Sort.by("id"));
        List<Compilation> compilations = compilationRepository.findAllByPinned(pinned, pageable).stream().collect(Collectors.toList());
        if (compilations.size() == 0) {
            throw new ExceptionNotFound("Список подборок пуст");
        }
        return compilations;
    }

    /**
     * Метод - получение подборки по id
     *
     * @param compId - id подборки
     */
    public Compilation getCompilationById(long compId) {
        Compilation compilation = compilationRepository.findById(compId).orElse(new Compilation());
        if (compilation.getId() == 0) {
            throw new ExceptionNotFound("Подборка не найдена");
        }
        return compilation;
    }
}
