package ru.practicum.ewm.events;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ewm.events.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
