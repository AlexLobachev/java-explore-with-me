package ru.practicum.ewm.mappers.request;


import ru.practicum.ewm.dtos.request.RequestDto;
import ru.practicum.ewm.models.request.Request;

/**
 * Класс маппер ДТО запросов
 *
 * @version 1.0
 * @autor Lobachev
 */
public class RequestMapper {
    /**
     * Метод - конвертировать Request -> RequestDto
     *
     * @param request - объект Request
     */
    public static RequestDto requestToDto(Request request) {
        RequestDto requestDto = new RequestDto();
        requestDto.setRequester(request.getRequester().getId());
        requestDto.setCreated(request.getCreated());
        requestDto.setStatus(request.getStatus());
        requestDto.setId(request.getId());
        requestDto.setEvent(request.getEvent().getId());
        return requestDto;
    }
}
