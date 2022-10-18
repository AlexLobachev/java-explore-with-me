package ru.practicum.ewm.services.request;


import ru.practicum.ewm.dtos.request.RequestDto;
import ru.practicum.ewm.models.request.Request;

import java.util.List;

/**
 * Интерфейс запросов.
 *
 * @version 1.0
 * @autor Lobachev
 */
public interface RequestService {
    /**
     * Метод - Получение информации о заявках текущего пользователя на участие в чужих событиях
     *
     * @param userId - id текущего пользователя
     */
    List<Request> getRequestUserAlienEvent(long userId);

    /**
     * Метод - Получение информации о заявках текущего пользователя на участие в чужих событиях
     *
     * @param userId  - id текущего пользователя
     * @param eventId - id события
     */
    Request createRequest(long userId, long eventId);

    /**
     * Метод - Отмена своего запроса на участие в событии
     *
     * @param userId    - id текущего пользователя
     * @param requestId - id события
     */
    RequestDto cancelRequest(long userId, long requestId);

    /**
     * Метод - Подтверждение чужой заявки на участие в событии текущего пользователя.
     *
     * @param requestId - id события
     */
    RequestDto confirmTheApplication(long requestId);

    /**
     * Метод - Отклонение чужой заявки на участие в событии текущего пользователя.
     *
     * @param requestId - id события
     */
    RequestDto rejectTheApplication(long requestId);


}
