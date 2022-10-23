package ru.practicum.ewm.services.event.publish;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.exceptions.ExceptionNotFound;
import ru.practicum.ewm.exceptions.ExclusionInvalidRequest;
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

/**
 * Класс бизнес логики событий. (публичный)
 *
 * @version 1.0
 * @autor Lobachev
 */
@Service
@RequiredArgsConstructor
@Transactional
public class PublicEventServiceImpl implements PublicEventService {
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
    public List<Event> getAllEvent(String text, List<Long> categories,
                                   Boolean paid, LocalDateTime rangeStart,
                                   LocalDateTime rangeEnd,
                                   Boolean onlyAvailable, String sort,
                                   Integer from, Integer size) {
        switch (sort) {
            case "EVENT_DATE":
                sort = "eventDate";
                break;
            case "VIEWS":
                sort = "views";
                break;
            default:
                throw new ExclusionInvalidRequest("Ошибка правила сортировки");
        }
        Pageable pageable = OffsetLimitPageable.of(from, size, Sort.by(sort));
        eventRepository.updateViewAll();
        return eventRepository.findPublicDate(text, categories, rangeStart, rangeEnd, paid, pageable).stream().collect(Collectors.toList());
    }

    /**
     * Метод - Получение подробной информации об опубликованном событии по его идентификатору
     *
     * @param id - текст для поиска в содержимом аннотации и подробном описании события
     */
    public Event getEventById(long id) {
        Event event = eventRepository.findById(id).orElse(new Event());
        if (event.getAnnotation() == null) {
            throw new ExceptionNotFound("Событие не найдено");
        }
        eventRepository.updateView(id);
        return eventRepository.findById(id).orElse(new Event());
    }
}
