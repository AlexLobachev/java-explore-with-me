package ru.practicum.ewm.service;

import ru.practicum.ewm.dto.ViewStatsDto;
import ru.practicum.ewm.model.Hit;

import java.time.LocalDateTime;
import java.util.List;

public interface HitService {
    Hit saveStatistics(Hit endpointHit);

    List<ViewStatsDto> getStatistics(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique);
}
