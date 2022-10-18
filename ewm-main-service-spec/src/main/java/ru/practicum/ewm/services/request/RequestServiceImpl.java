package ru.practicum.ewm.services.request;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.dtos.request.RequestDto;
import ru.practicum.ewm.exceptions.ExceptionNotFound;
import ru.practicum.ewm.mappers.request.RequestMapper;
import ru.practicum.ewm.models.event.Event;
import ru.practicum.ewm.models.request.Request;
import ru.practicum.ewm.repositories.event.EventRepository;
import ru.practicum.ewm.repositories.request.RequestRepository;
import ru.practicum.ewm.services.user.UserServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import static ru.practicum.ewm.mappers.request.RequestMapper.requestToDto;
import static ru.practicum.ewm.models.request.StatusRequest.*;

/**
 * Класс бизнес логики запросов.
 *
 * @version 1.0
 * @autor Lobachev
 */
@Service
@RequiredArgsConstructor
@Transactional
public class RequestServiceImpl implements RequestService {
    /**
     * Поле зависимость от репозитория запросов RequestRepository
     */
    private final RequestRepository requestRepository;
    /**
     * Поле зависимость от валидационного класса RequestValidator
     */
    private final RequestValidator requestValidator;
    /**
     * Поле зависимость от репозитория событий EventRepository
     */
    private final EventRepository eventRepository;
    /**
     * Поле зависимость от класса пользователей UserServiceImpl
     */
    private final UserServiceImpl userService;

    /**
     * Метод - Получение информации о заявках текущего пользователя на участие в чужих событиях
     *
     * @param userId - id текущего пользователя
     */
    public List<Request> getRequestUserAlienEvent(long userId) {
        List<Request> requests = requestRepository.findAllByRequesterId(userId);
        if (requests.size() == 0) {
            throw new ExceptionNotFound("Заявки не найдены");
        }
        return requests;
    }

    /**
     * Метод - Получение информации о заявках текущего пользователя на участие в чужих событиях
     *
     * @param userId  - id текущего пользователя
     * @param eventId - id события
     */
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

    /**
     * Метод - Отмена своего запроса на участие в событии
     *
     * @param userId    - id текущего пользователя
     * @param requestId - id события
     */
    public RequestDto cancelRequest(long userId, long requestId) {
        Request request = getRequestById(requestId);
        if (request.getEvent().getViews() != 0) {
            eventRepository.updateRequestsDelete(request.getEvent().getId());
        }
        requestRepository.updateRequestStatus(requestId, CANCELED);
        return requestToDto(getRequestById(requestId));
    }

    /**
     * Метод - Получение информации о запросах на участие в событии текущего пользователя.
     *
     * @param userId  - id текущего пользователя
     * @param eventId - id события
     */

    public List<RequestDto> getRequestsEventUser(long userId, long eventId) {
        return requestRepository.findAllByEventId(eventId).stream().map(RequestMapper::requestToDto).collect(Collectors.toList());
    }

    /**
     * Метод - Подтверждение чужой заявки на участие в событии текущего пользователя.
     *
     * @param requestId - id события
     */
    public RequestDto confirmTheApplication(long requestId) {
        requestRepository.updateRequestStatus(requestId, CONFIRMED);
        return requestToDto(requestRepository.findById(requestId).orElse(new Request()));
    }

    /**
     * Метод - Отклонение чужой заявки на участие в событии текущего пользователя.
     *
     * @param requestId - id события
     */
    public RequestDto rejectTheApplication(long requestId) {
        requestRepository.updateRequestStatus(requestId, REJECTED);
        return requestToDto(requestRepository.findById(requestId).orElse(new Request()));
    }

    /**
     * Метод - приватный метод для проверки существования запроса в БД.
     *
     * @param requestId - id события
     */
    private Request getRequestById(long requestId) {
        Request request = requestRepository.findById(requestId).orElse(new Request());
        if (request.getId() == 0) {
            throw new ExceptionNotFound("Запрос не найден");
        }
        return request;
    }


}
