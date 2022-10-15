package ru.practicum.ewm.events.dto;

import ru.practicum.ewm.events.model.Location;

public class LocationMapper {
    public static LocationDto toDtoLocation(Location location) {
        LocationDto locationDto = new LocationDto();
        locationDto.setLat(location.getLat());
        locationDto.setLon(location.getLon());
        return locationDto;
    }

}
