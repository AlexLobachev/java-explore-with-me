package ru.practicum.ewm.services.event.publish;

import ru.practicum.ewm.models.event.Event;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Интерфейс событий. (публичный)
 *
 * @version 1.0
 * @autor Lobachev
 */
public interface PublicEventService {
    /**
     * Метод - Получение событий с возможностью фильтрации
     *
     * @param text          - текст для поиска в содержимом аннотации и подробном описании события
     * @param categories    - список идентификаторов категорий в которых будет вестись поиск
     * @param paid          - поиск только платных/бесплатных событий
     * @param rangeStart    - дата и время не раньше которых должно произойти событие
     * @param rangeEnd      - дата и время не позже которых должно произойти событие
     * @param onlyAvailable - только события у которых не исчерпан лимит запросов на участие
     * @param sort          - Вариант сортировки: по дате события или по количеству просмотров
     * @param from          - с какого место необходимо искать подборку
     * @param size          - количество для вывода
     */
    List<Event> getAllEvent(String text, List<Long> categories,
                            Boolean paid, LocalDateTime rangeStart,
                            LocalDateTime rangeEnd,
                            Boolean onlyAvailable, String sort,
                            Integer from, Integer size);

    /**
     * Метод - Получение подробной информации об опубликованном событии по его идентификатору
     *
     * @param id - текст для поиска в содержимом аннотации и подробном описании события
     */
    Event getEventById(long id);
}
