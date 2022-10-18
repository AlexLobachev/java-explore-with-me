package ru.practicum.ewm.controllers.compilation.admin;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.client.compilation.admin.CompilationClientAdmin;
import ru.practicum.ewm.dtos.compilation.CompilationDto;

import javax.validation.Valid;

/**
 * Класс контроллер подборок событий. (для Администратора)
 *
 * @version 1.0
 * @autor Lobachev
 */
@Controller
@RequestMapping(path = "/admin/compilations")
@AllArgsConstructor
@Valid
public class CompilationControllerAdmin {
    /**
     * Поле зависимсость от сервисного класса CompilationClient
     */

    private final CompilationClientAdmin compilationClient;

    /**
     * Метод - создания подборки
     * Энпоинт - /admin/compilations
     *
     * @param compilationDto - сущность (подборка)
     */
    @PostMapping
    public ResponseEntity<Object> createCompilation(@Validated @RequestBody CompilationDto compilationDto) {
        return compilationClient.createCompilation(compilationDto);
    }

    /**
     * Метод - удаления подборки
     * Энпоинт - /admin/compilations/id
     *
     * @param compId - id подборки которую необходимо удалить
     */
    @DeleteMapping(value = "{compId}")
    public ResponseEntity<Object> deleteCompilation(@PathVariable long compId) {
        return compilationClient.deleteCompilation(compId);
    }

    /**
     * Метод - удаления события из подборки
     * Энпоинт - /admin/compilations/compId/event/eventId
     *
     * @param compId   - id подборки из которой удаоить
     * @param eventId- id события которое необходимо удалить
     */
    @DeleteMapping(value = "{compId}/events/{eventId}")
    public ResponseEntity<Object> deleteEventFromCompilation(@PathVariable long compId, @PathVariable long eventId) {
        return compilationClient.deleteEventFromCompilation(compId, eventId);
    }

    /**
     * Метод - добавления события в подборку
     * Энпоинт - /admin/compilations/compId/event/eventId
     *
     * @param compId   - id подборки в которую необходимо добавить событие
     * @param eventId- id события которое необходимо добавить
     */
    @PatchMapping(value = "{compId}/events/{eventId}")
    public ResponseEntity<Object> addEventToCompilation(@PathVariable long compId, @PathVariable long eventId) {
        return compilationClient.addEventToCompilation(compId, eventId);
    }

    /**
     * Метод - открепления подборки с главной страницы
     * Энпоинт - /admin/compilations/compId/pin
     *
     * @param compId - id подборки в которую необходимо открепить
     */
    @DeleteMapping(value = "{compId}/pin")
    public ResponseEntity<Object> unpinCompilation(@PathVariable long compId) {
        return compilationClient.unpinCompilation(compId);

    }

    /**
     * Метод - прикрепления подборки на главную страницу
     * Энпоинт - /admin/compilations/compId/pin
     *
     * @param compId - id подборки которую необходимо прикрепить
     */
    @PatchMapping(value = "{compId}/pin")
    public ResponseEntity<Object> pinCompilation(@PathVariable long compId) {
        return compilationClient.pinCompilation(compId);
    }
}
