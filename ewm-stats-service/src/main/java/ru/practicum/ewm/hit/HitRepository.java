package ru.practicum.ewm.hit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.ewm.hit.dto.ViewStats;
import ru.practicum.ewm.hit.model.Hit;

import java.time.LocalDateTime;
import java.util.List;

public interface HitRepository extends JpaRepository<Hit, Long> {
    @Query(value = "" +
            "select new ru.practicum.ewm.hit.dto.ViewStats (h.app,h.uri, count(h.uri))" +
            "from Hit h " +
            "where ?1 is null or h.timestamp >= ?1 and " +
            "?2 is null or h.timestamp <= ?2 and " +
            "(?3 is null or h.uri in ?3)" +
            "group by h.uri")
    List<ViewStats> getStatistics(LocalDateTime start, LocalDateTime end, List<String> uris);

    @Query(value = "" +
            "select new ru.practicum.ewm.hit.dto.ViewStats (h.app,h.uri, count(h.uri))" +
            "from Hit h " +
            "where ?1 is null or h.timestamp >= ?1 and " +
            "?2 is null or h.timestamp <= ?2 and " +
            "(?3 is null or h.uri in ?3)" +
            "group by h.uri having count (h.uri) = 1")
    List<ViewStats> getStatisticsUnique(LocalDateTime start, LocalDateTime end, List<String> uris);
}
