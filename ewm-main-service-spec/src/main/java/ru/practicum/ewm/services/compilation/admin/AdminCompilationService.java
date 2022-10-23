package ru.practicum.ewm.services.compilation.admin;

import ru.practicum.ewm.dtos.compilation.CompilationDto;
import ru.practicum.ewm.models.compilation.Compilation;

/**
 * Интерфейс бизнес логики подборок событий. (для Администратора)
 *
 * @version 1.0
 * @autor Lobachev
 */
public interface AdminCompilationService {
    /**
     * Метод - создания подборки
     *
     * @param compilationDto - сущность (подборка)
     */
    Compilation createCompilation(CompilationDto compilationDto);

    /**
     * Метод - удаления подборки
     *
     * @param compId - id подборки которую необходимо удалить
     */
    void deleteCompilation(long compId);

    /**
     * Метод - удаления события из подборки
     *
     * @param compId   - id подборки из которой удаоить
     * @param eventId- id события которое необходимо удалить
     */
    void deleteEventFromCompilation(long compId, long eventId);

    /**
     * Метод - добавления события в подборку
     *
     * @param compId   - id подборки в которую необходимо добавить событие
     * @param eventId- id события которое необходимо добавить
     */
    void addEventToCompilation(long compId, long eventId);

    /**
     * Метод - открепления подборки с главной страницы
     *
     * @param compId - id подборки в которую необходимо открепить
     */
    void unpinCompilation(long compId);

    /**
     * Метод - прикрепления подборки на главную страницу
     *
     * @param compId - id подборки которую необходимо прикрепить
     */
    void pinCompilation(long compId);
}
