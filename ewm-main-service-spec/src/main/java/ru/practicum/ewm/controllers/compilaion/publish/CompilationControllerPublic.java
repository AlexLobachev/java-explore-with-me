package ru.practicum.ewm.controllers.compilaion.publish;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dtos.compilation.CompilationDtoEvent;
import ru.practicum.ewm.mappers.compilation.CompilationMapper;
import ru.practicum.ewm.services.compilation.publish.PublishCompilationServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import static ru.practicum.ewm.mappers.compilation.CompilationMapper.compilationDtoEvent;

/**
 * Класс контроллер подборок событий. (публичный)
 *
 * @version 1.0
 * @autor Lobachev
 */
@RestController
@RequestMapping(path = "/compilations")
@RequiredArgsConstructor
public class CompilationControllerPublic {
    /**
     * Поле зависимсость от сервисного класса CompilationServiceImpl
     */
    private final PublishCompilationServiceImpl compilationService;

    /**
     * Метод - получение всех подборок
     * Энпоинт - /compilations
     *
     * @param pinned - условие поиска (закрепленные/незакрепленные подборки)
     * @param from   - с какого место необходимо искать подборку
     * @param size   - количество для вывода
     */
    @GetMapping
    public List<CompilationDtoEvent> getAllCompilation(@RequestParam Boolean pinned,
                                                       @RequestParam Integer from,
                                                       @RequestParam Integer size) {
        return compilationService.getAllCompilation(pinned, from, size).stream().map(CompilationMapper::compilationDtoEvent).collect(Collectors.toList());
    }

    /**
     * Метод - получение подборки по id
     * Энпоинт - /compilations
     *
     * @param compId - id подборки
     */
    @GetMapping(path = "{compId}")
    public CompilationDtoEvent getCompilationById(@PathVariable long compId) {
        return compilationDtoEvent(compilationService.getCompilationById(compId));
    }


}
