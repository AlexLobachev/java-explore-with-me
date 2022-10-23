package ru.practicum.ewm.controllers.compilation.publish;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.client.compilation.publish.CompilationClientPublic;

import javax.validation.constraints.PositiveOrZero;

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
     * Поле зависимсость от сервисного класса CompilationClient
     */

    private final CompilationClientPublic compilationClient;

    /**
     * Метод - получение всех подборок
     * Энпоинт - /compilations
     *
     * @param pinned - условие поиска (закрепленные/незакрепленные подборки)
     * @param from   - с какого место необходимо искать подборку
     * @param size   - количество для вывода
     */
    @GetMapping
    public ResponseEntity<Object> getAllCompilation(@RequestParam Boolean pinned,
                                                    @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                    @RequestParam Integer size) {
        return compilationClient.getAllCompilation(pinned, from, size);
    }

    /**
     * Метод - получение подборки по id
     * Энпоинт - /compilations
     *
     * @param compId - id подборки
     */
    @GetMapping(path = "{compId}")
    public ResponseEntity<Object> getCompilationById(@PathVariable long compId) {
        return compilationClient.getCompilationById(compId);
    }


}
