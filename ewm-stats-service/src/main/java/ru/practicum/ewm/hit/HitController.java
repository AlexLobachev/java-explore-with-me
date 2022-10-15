package ru.practicum.ewm.hit;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.hit.dto.ViewStats;
import ru.practicum.ewm.hit.model.Hit;
import ru.practicum.ewm.hit.service.HitServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class HitController {
    private final HitServiceImpl hitService;

    @PostMapping(path = "/hit")
    public Hit saveStatistics(@RequestBody Hit endpointHit) {
        return hitService.saveStatistics(endpointHit);
    }

    @GetMapping(path = "/stats")
    public List<ViewStats> getStatistics(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
                                         @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end,
                                         @RequestParam(required = false) List<String> uris, @RequestParam(defaultValue = "false") Boolean unique) {
        return hitService.getStatistics(start, end, uris, unique);
    }


}
