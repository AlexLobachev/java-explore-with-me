package ru.practicum.ewm.services.event.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.dtos.event.EventDto;
import ru.practicum.ewm.dtos.event.EventDtoFull;
import ru.practicum.ewm.exceptions.ExceptionNotFound;
import ru.practicum.ewm.models.event.Event;
import ru.practicum.ewm.pageable.OffsetLimitPageable;
import ru.practicum.ewm.repositories.event.EventRepository;
import ru.practicum.ewm.services.category.publish.PublicCategoryServiceImpl;
import ru.practicum.ewm.services.event.EventValidator;
import ru.practicum.ewm.services.request.RequestServiceImpl;
import ru.practicum.ewm.services.user.UserServiceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static ru.practicum.ewm.mappers.event.EventMapper.toDtoResponseEvent;
import static ru.practicum.ewm.models.event.State.*;

/**
 * Класс бизнес логики событий. (для администратора)
 *
 * @version 1.0
 * @autor Lobachev
 */
@Service
@RequiredArgsConstructor
@Transactional
public class AdminEventServiceImpl {
    /**
     * Поле зависимость от репозитория событий EventRepository
     */
    private final EventRepository eventRepository;

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

    public List<Event> getAllEventUser(List<Long> users, List<String> states,
                                       List<Integer> categories,
                                       LocalDateTime rangeStart, LocalDateTime rangeEnd,
                                       Integer from, Integer size) {
        Pageable pageable = OffsetLimitPageable.of(from, size, Sort.by("id"));
        List<Event> events = eventRepository.findAdmin(users, states,
                categories,
                rangeStart, rangeEnd,
                pageable).stream().collect(Collectors.toList());


        if (events.size() == 0) {
            throw new ExceptionNotFound("Событий не найдено");
        }
        return events;
    }

    /**
     * Метод - Редактирование данных любого события администратором.
     * Энпоинт - /admin/events/id
     *
     * @param eventId  - id события
     * @param eventDto - параметры редактирования
     */
    public EventDtoFull editingAnEvent(long eventId, EventDto eventDto) {

        Event event = eventRepository.findById(eventDto.getEventId()).orElse(new Event());
        event.setCategory(categoryService.getCategoryById(eventDto.getCategory()));
        event.setState(PENDING);

        eventRepository.updateEvent(event.getInitiator().getId(), eventDto.getAnnotation(), event.getCategory(), eventDto.getDescription(),
                eventDto.getEventDate(), eventDto.getEventId(), eventDto.getPaid(), eventDto.getParticipantLimit(), eventDto.getTitle(),
                event.getState(), eventDto.getRequestModeration());
        return toDtoResponseEvent(eventRepository.findById(eventDto.getEventId()).orElse(new Event()));
    }

    /**
     * Метод - Публикация события.
     *
     * @param eventId - id события
     */
    public EventDtoFull rejectionEvent(long eventId) {
        eventRepository.eventState(eventId, CANCELED);
        return toDtoResponseEvent(eventRepository.findById(eventId).orElse(new Event()));
    }

    /**
     * Метод - Отклонение события.
     *
     * @param eventId - id события
     */
    public Event publishEvent(long eventId) {
        eventRepository.eventState(eventId, PUBLISHED);
        return eventRepository.findById(eventId).orElse(new Event());
    }
}
