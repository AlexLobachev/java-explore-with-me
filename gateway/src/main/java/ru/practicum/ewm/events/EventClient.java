package ru.practicum.ewm.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.ewm.client.BaseClient;
import ru.practicum.ewm.events.dto.EventDto;

import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EventClient extends BaseClient {
    private static final String API_PREFIX = "";

    @Autowired
    public EventClient(@Value("${ewm-main-service-spec.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        )
        ;
    }


    /**
     * PRIVATE  EVENT
     */
    public ResponseEntity<Object> getAllEventUser(long userId, Integer from, Integer size) {
        Map<String, Object> parameters = Map.of(
                "from", from,
                "size", size);
        return get("/users/" + userId + "/events?from={from}&size={size}", parameters);
    }

    public ResponseEntity<Object> updateEvent(long userId, EventDto eventDto) {
        checkEventDate(eventDto);
        return patch("/users/" + userId + "/events", eventDto);
    }


    public ResponseEntity<Object> createEvent(long userId, EventDto eventDto) {
        checkEventDate(eventDto);
        return post("/users/" + userId + "/events", eventDto);
    }

    public ResponseEntity<Object> getEventUser(long userId, long eventId) {

        return get("/users/" + userId + "/events/" + eventId);
    }

    public ResponseEntity<Object> cancelingUpdateEvent(long userId, long eventId) {
        return patch("/users/" + userId + "/events/" + eventId);
    }

    public ResponseEntity<Object> getRequestsEventUser(long userId, long eventId) {
        return get("/users/" + userId + "/events/" + eventId + "/requests");
    }

    public ResponseEntity<Object> confirmTheApplication(long userId, long eventId, long reqId) {
        return patch("/users/" + userId + "/events/" + eventId + "/requests/" + reqId + "/confirm");

    }

    public ResponseEntity<Object> rejectTheApplication(long userId, long eventId, long reqId) {

        return patch("/users/" + userId + "/events/" + eventId + "/requests/" + reqId + "/reject");
    }

    /**
     * ADMIN  EVENT
     */
    public ResponseEntity<Object> getAllEventUser(List<Long> users1, List<String> states1,
                                                  List<Integer> categories1,
                                                  LocalDateTime rangeStart1, LocalDateTime rangeEnd1,
                                                  Integer from, Integer size) {

        String users = users1.stream().map(Object::toString)
                .collect(Collectors.joining(" "));
        String states = states1.stream().map(Object::toString)
                .collect(Collectors.joining(" "));
        String categories = categories1.stream().map(Object::toString)
                .collect(Collectors.joining(" "));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String rangeEnd = rangeEnd1.format(formatter);
        String rangeStart = rangeStart1.format(formatter);

        Map<String, Object> parameters = Map.of(
                "users", users,
                "states", states,
                "categories", categories,
                "rangeStart", rangeStart,
                "rangeEnd", rangeEnd,
                "from", from,
                "size", size
        );

        return get("/admin/events?users={users}&states={states}&categories={categories}&rangeStart={rangeStart}&rangeEnd={rangeEnd}&from={from}&size={size}", parameters);
    }

    public ResponseEntity<Object> editingAnEvent(long eventId, EventDto eventDto) {

        return put("/admin/events/" + eventId, eventDto);
    }

    public ResponseEntity<Object> rejectionEvent(long eventId) {

        return patch("/admin/events/" + eventId + "/reject", eventId);
    }

    public ResponseEntity<Object> publishEvent(long eventId) {
        return patch("/admin/events/" + eventId + "/publish", eventId);
    }

    /**
     * PUBLIC  EVENT
     */
    public ResponseEntity<Object> getAllEvent(String text, List<Long> categories1,
                                              Boolean paid, LocalDateTime rangeStart1,
                                              LocalDateTime rangeEnd1,
                                              Boolean onlyAvailable, String sort,
                                              Integer from, Integer size) {

        String categories = categories1.stream().map(Object::toString)
                .collect(Collectors.joining(" "));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String rangeEnd = rangeEnd1.format(formatter);
        String rangeStart = rangeStart1.format(formatter);


        Map<String, Object> parameters = Map.of(
                "text", text,
                "categories", categories,
                "paid", paid,
                "rangeStart", rangeStart,
                "rangeEnd", rangeEnd,
                "onlyAvailable", onlyAvailable,
                "sort", sort,
                "from", from,
                "size", size
        );

        return get("/events?text={text}&categories={categories}&paid={paid}&rangeStart={rangeStart}&rangeEnd={rangeEnd}&onlyAvailable={onlyAvailable}&sort={sort}&from={from}&size={size}", parameters);
    }

    public ResponseEntity<Object> getEventById(long id) {
        return get("/events/" + id);
    }

    private void checkEventDate(EventDto eventDto) {

        if (eventDto.getEventDate().isBefore(LocalDateTime.now().plusHours(2))) {
            throw new ValidationException("Дата и время на которые намечено событие не может быть раньше, чем через два часа от текущего момента");
        }

    }

}
