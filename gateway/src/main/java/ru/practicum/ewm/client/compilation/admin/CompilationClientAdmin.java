package ru.practicum.ewm.client.compilation.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.ewm.client.base.BaseClient;
import ru.practicum.ewm.dtos.compilation.CompilationDto;

/**
 * Класс клиент подборок событий. (для Администратора)
 *
 * @version 1.0
 * @autor Lobachev
 */
@Service
public class CompilationClientAdmin extends BaseClient {
    /**
     * Поле константа URL
     */
    private static final String API_PREFIX = "";

    /**
     * Конструктор - создание нового объекта
     *
     * @see CompilationClientAdmin (String, RestTemplateBuilder )
     */
    @Autowired
    public CompilationClientAdmin(@Value("${ewm-main-service-spec.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        )
        ;
    }

    /**
     * Метод - создания подборки
     *
     * @param compilationDto - сущность (подборка)
     */
    public ResponseEntity<Object> createCompilation(CompilationDto compilationDto) {

        return post("/admin/compilations", compilationDto);
    }

    /**
     * Метод - удаления подборки
     *
     * @param compId - id подборки которую необходимо удалить
     */
    public ResponseEntity<Object> deleteCompilation(long compId) {
        return delete("/admin/compilations/" + compId);
    }

    /**
     * Метод - удаления события из подборки
     *
     * @param compId   - id подборки из которой удаоить
     * @param eventId- id события которое необходимо удалить
     */
    public ResponseEntity<Object> deleteEventFromCompilation(long compId, long eventId) {
        return delete("/admin/compilations/" + compId + "/events/" + eventId);
    }

    /**
     * Метод - добавления события в подборку
     *
     * @param compId   - id подборки в которую необходимо добавить событие
     * @param eventId- id события которое необходимо добавить
     */
    public ResponseEntity<Object> addEventToCompilation(long compId, long eventId) {
        return patch("/admin/compilations/" + compId + "/events/" + eventId);
    }

    /**
     * Метод - открепления подборки с главной страницы
     *
     * @param compId - id подборки в которую необходимо открепить
     */
    public ResponseEntity<Object> unpinCompilation(long compId) {
        return delete("/admin/compilations/" + compId + "/pin");
    }

    /**
     * Метод - прикрепления подборки на главную страницу
     *
     * @param compId - id подборки которую необходимо прикрепить
     */
    public ResponseEntity<Object> pinCompilation(long compId) {
        return delete("/admin/compilations/" + compId + "/pin");
    }
}
