package ru.practicum.ewm.mappers.location;


import ru.practicum.ewm.dtos.location.LocationDto;
import ru.practicum.ewm.models.location.Location;

/**
 * Класс маппер ДТО локаций
 *
 * @version 1.0
 * @autor Lobachev
 */
public class LocationMapper {
    /**
     * Метод - конвертировать Location -> LocationDto
     *
     * @param location - объект Location
     */
    public static LocationDto toDtoLocation(Location location) {
        LocationDto locationDto = new LocationDto();
        locationDto.setLat(location.getLat());
        locationDto.setLon(location.getLon());
        return locationDto;
    }

}
