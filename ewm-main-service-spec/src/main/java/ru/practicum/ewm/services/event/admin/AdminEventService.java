package ru.practicum.ewm.services.event.admin;

import ru.practicum.ewm.dtos.event.EventDtoFull;
import ru.practicum.ewm.models.event.Event;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Интерфейс событий. (для администратора)
 *
 * @version 1.0
 * @autor Lobachev
 */
public interface AdminEventService {
    /**
     * Метод - получение всех событий подходящих под переданные условия
     *
     * @param users      - список id пользователей, чьи события нужно найти
     * @param states     - список состояний в которых находятся искомые события
     * @param categories - список id категорий в которых будет вестись поиск
     * @param rangeStart - дата и время не раньше которых должно произойти событие
     * @param rangeEnd   - дата и время не позже которых должно произойти событие
     * @param from       - с какого место необходимо искать подборку
     * @param size       - количество для вывода
     */
    List<Event> getAllEventUser(List<Long> users, List<String> states,
                                List<Integer> categories,
                                LocalDateTime rangeStart, LocalDateTime rangeEnd,
                                Integer from, Integer size);

    /**
     * Метод - Публикация события.
     *
     * @param eventId - id события
     */
    EventDtoFull rejectionEvent(long eventId);

    /**
     * Метод - Отклонение события.
     *
     * @param eventId - id события
     */
    Event publishEvent(long eventId);
}
