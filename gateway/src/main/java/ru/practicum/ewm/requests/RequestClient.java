package ru.practicum.ewm.requests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.ewm.client.BaseClient;
import ru.practicum.ewm.requests.dto.RequestDto;

import java.util.Map;


@Service
public class RequestClient extends BaseClient {
    private static final String API_PREFIX = "";


    @Autowired
    public RequestClient(@Value("${ewm-main-service-spec.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        )
        ;
    }

    public ResponseEntity<Object> getRequestUserAlienEvent(long userId) {

        return get("/users/" + userId + "/requests");
    }

    public ResponseEntity<Object> createRequest(long userId, long eventId) {
        RequestDto requestDto = new RequestDto();
        Map<String, Object> parameters = Map.of(
                "eventId", eventId

        );
        return post("/users/" + userId + "/requests?eventId={eventId}", userId, parameters, requestDto);
    }

    public ResponseEntity<Object> cancelRequest(long userId, long requestId) {
        return patch("/users/" + userId + "/requests/" + requestId + "/cancel");
    }


}
