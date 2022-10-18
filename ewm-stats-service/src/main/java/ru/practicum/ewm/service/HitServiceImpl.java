package ru.practicum.ewm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.dto.ViewStatsDto;
import ru.practicum.ewm.model.Hit;
import ru.practicum.ewm.repository.HitRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Класс бизнес логики.
 *
 * @version 1.0
 * @autor Lobachev
 */
@Service
@RequiredArgsConstructor
public class HitServiceImpl implements HitService {
    /**
     * Поле зависимсость от репозитория
     */
    private final HitRepository hitRepository;

    /**
     * Метод - сохранение статистики
     * Энпоинт - /hit
     *
     * @param endpointHit - сущность (статистики)
     */
    public Hit saveStatistics(Hit endpointHit) {

        return hitRepository.save(endpointHit);
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
    public List<ViewStatsDto> getStatistics(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique) {
        if (!unique) {
            return hitRepository.getStatistics(start, end, uris);
        }
        return hitRepository.getStatisticsUnique(start, end, uris);
    }

}
