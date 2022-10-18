package ru.practicum.ewm.services.event.privat;

import ru.practicum.ewm.dtos.event.EventDto;
import ru.practicum.ewm.dtos.event.EventDtoFull;
import ru.practicum.ewm.dtos.request.RequestDto;
import ru.practicum.ewm.models.event.Event;

import java.util.List;

/**
 * Интерфейс событий. (приватный)
 *
 * @version 1.0
 * @autor Lobachev
 */
public interface PrivateEventService {
    /**
     * Метод - Получение событий, добавленных текущим пользователем
     *
     * @param userId - id текущего пользователя
     * @param from   - с какого место необходимо искать подборку
     * @param size   - количество для вывода
     */
    List<Event> getAllEventUser(long userId, Integer from, Integer size);

    /**
     * Метод - Изменение события добавленного текущим пользователем
     *
     * @param userId   - id текущего пользователя
     * @param eventDto - параметры редактирования
     */
    EventDtoFull updateEvent(long userId, EventDto eventDto);

    /**
     * Метод - Добавление нового события
     *
     * @param userId   - id текущего пользователя
     * @param eventDto - параметры события
     */
    Event createEvent(long userId, EventDto eventDto);

    /**
     * Метод - Получение полной информации о событии добавленном текущим пользователем
     * Энпоинт - /users/id/events
     *
     * @param userId  - id текущего пользователя
     * @param eventId - id события
     */
    Event getEventUser(long userId, long eventId);

    /**
     * Метод - Отмена события добавленного текущим пользователем.
     *
     * @param userId  - id текущего пользователя
     * @param eventId - id события
     */
    EventDtoFull cancelingUpdateEvent(long userId, long eventId);

    /**
     * Метод - Получение информации о запросах на участие в событии текущего пользователя.
     *
     * @param userId  - id текущего пользователя
     * @param eventId - id события
     */
    List<RequestDto> getRequestsEventUser(long userId, long eventId);

    /**
     * Метод - Подтверждение чужой заявки на участие в событии текущего пользователя.
     *
     * @param userId  - id текущего пользователя
     * @param eventId - id события
     * @param reqId   - id заявки, которую подтверждает текущий пользователь
     */
    RequestDto confirmTheApplication(long userId, long eventId, long reqId);

    /**
     * Метод - Отклонение чужой заявки на участие в событии текущего пользователя.
     *
     * @param userId  - id текущего пользователя
     * @param eventId - id события
     * @param reqId   - id заявки, которую подтверждает текущий пользователь
     */
    RequestDto rejectTheApplication(long userId, long eventId, long reqId);
}
