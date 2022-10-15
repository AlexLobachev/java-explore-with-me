package ru.practicum.ewm.hit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.hit.HitRepository;
import ru.practicum.ewm.hit.dto.ViewStats;
import ru.practicum.ewm.hit.model.Hit;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HitServiceImpl implements HitService {
    private final HitRepository hitRepository;

    public Hit saveStatistics(Hit endpointHit) {
        return hitRepository.save(endpointHit);
    }

    public List<ViewStats> getStatistics(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique) {
        if (!unique) {
            return hitRepository.getStatistics(start, end, uris);
        }
        return hitRepository.getStatisticsUnique(start, end, uris);
    }
}
