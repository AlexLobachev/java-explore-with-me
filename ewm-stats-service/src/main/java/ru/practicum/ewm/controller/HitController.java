package ru.practicum.ewm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.ViewStatsDto;
import ru.practicum.ewm.model.Hit;
import ru.practicum.ewm.service.HitServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Класс контроллер статистики.
 *
 * @version 1.0
 * @autor Lobachev
 */
@RestController
@RequestMapping
@RequiredArgsConstructor
public class HitController {
    /**
     * Поле зависимсость от сервисного класса Hit
     */
    private final HitServiceImpl hitService;

    /**
     * Метод - сохранение статистики
     * Энпоинт - /hit
     *
     * @param endpointHit - сущность (статистики)
     */
    @PostMapping(path = "/hit")
    public Hit saveStatistics(@RequestBody Hit endpointHit) {
        return hitService.saveStatistics(endpointHit);
    }

    /**
     * Метод - получение статистики
     * Энпоинт - /stats
     *
     * @param start  - дата с которой выгрузить статистику
     * @param end    - дата по которую выгрузить статистику
     * @param uris   - URL по которым интересует статистика
     * @param unique - уникальность URL
     */
    @GetMapping(path = "/stats")
    public List<ViewStatsDto> getStatistics(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
                                            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end,
                                            @RequestParam(required = false) List<String> uris, @RequestParam(defaultValue = "false") Boolean unique) {
        return hitService.getStatistics(start, end, uris, unique);
    }


}
