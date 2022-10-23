package ru.practicum.ewm.controllers.compilaion.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dtos.compilation.CompilationDto;
import ru.practicum.ewm.dtos.compilation.CompilationDtoEvent;
import ru.practicum.ewm.services.compilation.admin.AdminCompilationServiceImpl;

import static ru.practicum.ewm.mappers.compilation.CompilationMapper.compilationDtoEvent;

/**
 * Класс контроллер подборок событий. (для Администратора)
 *
 * @version 1.0
 * @autor Lobachev
 */
@RestController
@RequestMapping(path = "/admin/compilations")
@RequiredArgsConstructor
public class CompilationControllerAdmin {
    /**
     * Поле зависимсость от сервисного класса CompilationServiceImpl
     */
    private final AdminCompilationServiceImpl compilationService;

    /**
     * Метод - создания подборки
     * Энпоинт - /admin/compilations
     *
     * @param compilationDto - сущность (подборка)
     */
    @PostMapping
    public CompilationDtoEvent createCompilation(@RequestBody CompilationDto compilationDto) {
        return compilationDtoEvent(compilationService.createCompilation(compilationDto));
    }

    /**
     * Метод - удаления подборки
     * Энпоинт - /admin/compilations/id
     *
     * @param compId - id подборки которую необходимо удалить
     */
    @DeleteMapping(value = "{compId}")
    public void deleteCompilation(@PathVariable long compId) {
        compilationService.deleteCompilation(compId);
    }

    /**
     * Метод - удаления события из подборки
     * Энпоинт - /admin/compilations/compId/event/eventId
     *
     * @param compId   - id подборки из которой удаоить
     * @param eventId- id события которое необходимо удалить
     */
    @DeleteMapping(value = "{compId}/events/{eventId}")
    public void deleteEventFromCompilation(@PathVariable long compId, @PathVariable long eventId) {
        compilationService.deleteEventFromCompilation(compId, eventId);
    }

    /**
     * Метод - добавления события в подборку
     * Энпоинт - /admin/compilations/compId/event/eventId
     *
     * @param compId   - id подборки в которую необходимо добавить событие
     * @param eventId- id события которое необходимо добавить
     */
    @PatchMapping(value = "{compId}/events/{eventId}")
    public void addEventToCompilation(@PathVariable long compId, @PathVariable long eventId) {
        compilationService.addEventToCompilation(compId, eventId);
    }

    /**
     * Метод - открепления подборки с главной страницы
     * Энпоинт - /admin/compilations/compId/pin
     *
     * @param compId - id подборки в которую необходимо открепить
     */
    @DeleteMapping(value = "{compId}/pin")
    public void unpinCompilation(@PathVariable long compId) {
        compilationService.unpinCompilation(compId);

    }

    /**
     * Метод - прикрепления подборки на главную страницу
     * Энпоинт - /admin/compilations/compId/pin
     *
     * @param compId - id подборки которую необходимо прикрепить
     */
    @PatchMapping(value = "{compId}/pin")
    public void pinCompilation(@PathVariable long compId) {
        compilationService.pinCompilation(compId);
    }
}
