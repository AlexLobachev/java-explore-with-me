package ru.practicum.ewm.requests.dto;

import ru.practicum.ewm.requests.model.Request;

public class RequestMapper {
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
