package ru.practicum.ewm.services.event.privat;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.dtos.event.EventDto;
import ru.practicum.ewm.dtos.event.EventDtoFull;
import ru.practicum.ewm.dtos.request.RequestDto;
import ru.practicum.ewm.exceptions.ExceptionNotFound;
import ru.practicum.ewm.models.event.Event;
import ru.practicum.ewm.pageable.OffsetLimitPageable;
import ru.practicum.ewm.repositories.event.EventRepository;
import ru.practicum.ewm.repositories.location.LocationRepository;
import ru.practicum.ewm.services.category.publish.PublicCategoryServiceImpl;
import ru.practicum.ewm.services.event.EventValidator;
import ru.practicum.ewm.services.request.RequestServiceImpl;
import ru.practicum.ewm.services.user.UserServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import static ru.practicum.ewm.mappers.event.EventMapper.toDtoResponseEvent;
import static ru.practicum.ewm.mappers.event.EventMapper.toEventDto;
import static ru.practicum.ewm.models.event.State.CANCELED;
import static ru.practicum.ewm.models.event.State.PENDING;

/**
 * Класс бизнес логики событий. (приватный)
 *
 * @version 1.0
 * @autor Lobachev
 */
@Service
@RequiredArgsConstructor
@Transactional
public class PrivateEventServiceImpl implements PrivateEventService {
    /**
     * Поле зависимость от репозитория событий EventRepository
     */
    private final EventRepository eventRepository;
    /**
     * Поле зависимость от репозитория локаций LocationRepository
     */
    private final LocationRepository locationRepository;
    /**
     * Поле зависимость от класса пользователей UserServiceImpl
     */
    private final UserServiceImpl userService;
    /**
     * Поле зависимость от класса категорий CategoryServiceImpl
     */
    private final PublicCategoryServiceImpl categoryService;
    /**
     * Поле зависимость от валидационного класса событий EventValidator
     */
    private final EventValidator eventValidator;
    /**
     * Поле зависимость от класса запросов RequestServiceImpl
     */
    private final RequestServiceImpl requestService;

    /**
     * Метод - Получение событий, добавленных текущим пользователем
     *
     * @param userId - id текущего пользователя
     * @param from   - с какого место необходимо искать подборку
     * @param size   - количество для вывода
     */
    public List<Event> getAllEventUser(long userId, Integer from, Integer size) {
        Pageable pageable = OffsetLimitPageable.of(from, size, Sort.by("id"));
        List<Event> events = eventRepository.findAllByInitiatorId(userId, pageable).stream().collect(Collectors.toList());
        if (events.size() == 0) {
            throw new ExceptionNotFound("Событий не найдено");
        }
        return events;
    }

    /**
     * Метод - Изменение события добавленного текущим пользователем
     *
     * @param userId   - id текущего пользователя
     * @param eventDto - параметры редактирования
     */
    public EventDtoFull updateEvent(long userId, EventDto eventDto) {
        Event event = eventRepository.findByInitiatorId(userId);
        eventValidator.checkUpdateState(event);
        event.setCategory(categoryService.getCategoryById(eventDto.getCategory()));
        event.setState(PENDING);
        eventRepository.updateEvent(userId, eventDto.getAnnotation(), event.getCategory(), eventDto.getDescription(),
                eventDto.getEventDate(), eventDto.getEventId(), eventDto.getPaid(), eventDto.getParticipantLimit(), eventDto.getTitle(),
                event.getState(), eventDto.getRequestModeration());
        return toDtoResponseEvent(eventRepository.findById(event.getId()).orElse(new Event()));
    }

    /**
     * Метод - Добавление нового события
     *
     * @param userId   - id текущего пользователя
     * @param eventDto - параметры события
     */

    public Event createEvent(long userId, EventDto eventDto) {
        Event event = toEventDto(eventDto);
        event.setLocation(locationRepository.save(eventDto.getLocation()));
        event.setInitiator(userService.getUser(userId));
        event.setCategory(categoryService.getCategoryById(eventDto.getCategory()));
        event.setState(PENDING);
        return eventRepository.save(event);
    }

    /**
     * Метод - Получение полной информации о событии добавленном текущим пользователем
     * Энпоинт - /users/id/events
     *
     * @param userId  - id текущего пользователя
     * @param eventId - id события
     */
    public Event getEventUser(long userId, long eventId) {
        return eventRepository.findByInitiatorIdAndId(userId, eventId);
    }

    /**
     * Метод - Отмена события добавленного текущим пользователем.
     *
     * @param userId  - id текущего пользователя
     * @param eventId - id события
     */
    public EventDtoFull cancelingUpdateEvent(long userId, long eventId) {
        eventRepository.cancelingUpdateEvent(eventId, CANCELED);
        return toDtoResponseEvent(eventRepository.findById(eventId).orElse(new Event()));
    }

    /**
     * Метод - Получение информации о запросах на участие в событии текущего пользователя.
     *
     * @param userId  - id текущего пользователя
     * @param eventId - id события
     */
    public List<RequestDto> getRequestsEventUser(long userId, long eventId) {
        return requestService.getRequestsEventUser(userId, eventId);
    }

    /**
     * Метод - Подтверждение чужой заявки на участие в событии текущего пользователя.
     *
     * @param userId  - id текущего пользователя
     * @param eventId - id события
     * @param reqId   - id заявки, которую подтверждает текущий пользователь
     */
    public RequestDto confirmTheApplication(long userId, long eventId, long reqId) {
        Event event = eventRepository.findById(eventId).orElse(new Event());
        eventValidator.checkRequestAdd(event);
        eventRepository.updateRequestsAdd(eventId);
        return requestService.confirmTheApplication(reqId);

    }

    /**
     * Метод - Отклонение чужой заявки на участие в событии текущего пользователя.
     *
     * @param userId  - id текущего пользователя
     * @param eventId - id события
     * @param reqId   - id заявки, которую подтверждает текущий пользователь
     */
    public RequestDto rejectTheApplication(long userId, long eventId, long reqId) {
        return requestService.rejectTheApplication(reqId);
    }
}
