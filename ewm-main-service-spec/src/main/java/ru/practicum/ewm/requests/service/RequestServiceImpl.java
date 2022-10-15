package ru.practicum.ewm.requests.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.events.EventRepository;
import ru.practicum.ewm.events.model.Event;
import ru.practicum.ewm.exception.ExceptionNotFound;
import ru.practicum.ewm.requests.RequestRepository;
import ru.practicum.ewm.requests.RequestValidator;
import ru.practicum.ewm.requests.dto.RequestDto;
import ru.practicum.ewm.requests.dto.RequestMapper;
import ru.practicum.ewm.requests.model.Request;
import ru.practicum.ewm.users.service.UserServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import static ru.practicum.ewm.requests.dto.RequestMapper.requestToDto;
import static ru.practicum.ewm.requests.model.StatusRequest.*;

@Service
@RequiredArgsConstructor
@Transactional
public class RequestServiceImpl implements RequestService {
    private final RequestRepository requestRepository;
    private final RequestValidator requestValidator;
    private final EventRepository eventRepository;
    private final UserServiceImpl userService;


    public List<Request> getRequestUserAlienEvent(long userId) {
        List<Request> requests = requestRepository.findAllByRequesterId(userId);
        if (requests.size() == 0) {
            throw new ExceptionNotFound("Заявки не найдены");
        }
        return requests;
    }

    public Request createRequest(long userId, long eventId) {
        Request request = new Request();
        request.setRequester(userService.getUser(userId));
        request.setEvent(eventRepository.findById(eventId).orElse(new Event()));
        requestValidator.checkCreateRequest(request, userId);
        if (!request.getEvent().getRequestModeration()) {
            eventRepository.updateRequestsAdd(eventId);
            request.setStatus(PUBLISHED);
        }
        return requestRepository.save(request);
    }

    public RequestDto cancelRequest(long userId, long requestId) {
        Request request = getRequestById(requestId);
        if (request.getEvent().getViews() != 0) {
            eventRepository.updateRequestsDelete(request.getEvent().getId());
        }
        requestRepository.updateRequestStatus(requestId, CANCELED);
        return requestToDto(getRequestById(requestId));
    }

    public List<RequestDto> getRequestsEventUser(long userId, long eventId) {
        return requestRepository.findAllByEventId(eventId).stream().map(RequestMapper::requestToDto).collect(Collectors.toList());
    }

    public RequestDto confirmTheApplication(long requestId) {
        requestRepository.updateRequestStatus(requestId, CONFIRMED);
        return requestToDto(requestRepository.findById(requestId).orElse(new Request()));
    }

    public RequestDto rejectTheApplication(long requestId) {
        requestRepository.updateRequestStatus(requestId, REJECTED);
        return requestToDto(requestRepository.findById(requestId).orElse(new Request()));
    }

    private Request getRequestById(long requestId) {
        Request request = requestRepository.findById(requestId).orElse(new Request());
        if (request.getId() == 0) {
            throw new ExceptionNotFound("Запрос не найден");
        }
        return request;
    }


}
