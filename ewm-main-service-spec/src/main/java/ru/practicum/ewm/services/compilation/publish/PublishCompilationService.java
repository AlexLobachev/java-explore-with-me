package ru.practicum.ewm.services.compilation.publish;

import ru.practicum.ewm.models.compilation.Compilation;

import java.util.List;

/**
 * Интерфейс бизнес логики подборок событий. (публичный)
 *
 * @version 1.0
 * @autor Lobachev
 */
public interface PublishCompilationService {
    /**
     * Метод - получение всех подборок
     *
     * @param pinned - условие поиска (закрепленные/незакрепленные подборки)
     * @param from   - с какого место необходимо искать подборку
     * @param size   - количество для вывода
     */
    List<Compilation> getAllCompilation(Boolean pinned, Integer from, Integer size);

    /**
     * Метод - получение подборки по id
     *
     * @param compId - id подборки
     */
    Compilation getCompilationById(long compId);
}
