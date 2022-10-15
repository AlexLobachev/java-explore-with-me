package ru.practicum.ewm.compilations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.ewm.client.BaseClient;
import ru.practicum.ewm.compilations.dto.CompilationDto;

import java.util.Map;


@Service
public class CompilationClient extends BaseClient {
    private static final String API_PREFIX = "";

    @Autowired
    public CompilationClient(@Value("${ewm-main-service-spec.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        )
        ;
    }

    public ResponseEntity<Object> createCompilation(CompilationDto compilationDto) {

        return post("/admin/compilations", compilationDto);
    }

    public ResponseEntity<Object> deleteCompilation(long compId) {
        return delete("/admin/compilations/" + compId);
    }

    public ResponseEntity<Object> deleteEventFromCompilation(long compId, long eventId) {
        return delete("/admin/compilations/" + compId + "/events/" + eventId);
    }

    public ResponseEntity<Object> addEventToCompilation(long compId, long eventId) {
        return patch("/admin/compilations/" + compId + "/events/" + eventId);
    }

    public ResponseEntity<Object> unpinCompilation(long compId) {
        getCompilationById(compId);
        return delete("/admin/compilations/" + compId + "/pin");
    }

    public ResponseEntity<Object> pinCompilation(long compId) {
        return delete("/admin/compilations/" + compId + "/pin");
    }

    public ResponseEntity<Object> getAllCompilation(Boolean pinned, Integer from, Integer size) {
        Map<String, Object> parameters = Map.of(
                "pinned", pinned,
                "from", from,
                "size", size
        );
        return get("/compilations?pinned={pinned}&from={from}&size={size}", parameters);
    }

    public ResponseEntity<Object> getCompilationById(long compId) {

        return get("/compilations/" + compId);
    }

}
