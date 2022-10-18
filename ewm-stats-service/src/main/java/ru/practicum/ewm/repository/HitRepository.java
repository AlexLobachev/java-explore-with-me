package ru.practicum.ewm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.ewm.dto.ViewStatsDto;
import ru.practicum.ewm.model.Hit;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Интерфейс репозиторий статистики.
 *
 * @version 1.0
 * @autor Lobachev
 */
@Repository
public interface HitRepository extends JpaRepository<Hit, Long> {
    /**
     * Метод - получение статистики по посещениям не учитывая уникальный ip.
     *
     * @param start - время с которого начать поиск
     * @param end   - время до которого искать
     * @param uris  - список URL
     */
    @Query(value = "" +
            "select new ru.practicum.ewm.dto.ViewStatsDto (h.app,h.uri, count(h.ip))" +
            "from Hit h " +
            "where h.timestamp >= ?1 and " +
            "h.timestamp <= ?2 and " +
            "(?3 is null or h.uri in ?3)" +
            "group by h.uri,h.app")
    List<ViewStatsDto> getStatistics(LocalDateTime start, LocalDateTime end, List<String> uris);

    /**
     * Метод - получение статистики по посещениям учитывая уникальный ip.
     *
     * @param start - время с которого начать поиск
     * @param end   - время до которого искать
     * @param uris  - список URL
     */
    @Query(value = "" +
            "select new ru.practicum.ewm.dto.ViewStatsDto (h.app,h.uri, count(h.ip))" +
            "from Hit h " +
            "where h.timestamp >= ?1 and " +
            "h.timestamp <= ?2 and " +
            "(?3 is null or h.uri in ?3)" +
            "group by h.uri,h.app having count (h.ip) = 1")
    List<ViewStatsDto> getStatisticsUnique(LocalDateTime start, LocalDateTime end, List<String> uris);


}
