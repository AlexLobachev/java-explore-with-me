package ru.practicum.ewm.repositories.location;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ewm.models.location.Location;

/**
 * Интерфейс репозиторий локаций.
 *
 * @version 1.0
 * @autor Lobachev
 */
public interface LocationRepository extends JpaRepository<Location, Long> {
}
