package ru.practicum.ewm.mappers.user;


import ru.practicum.ewm.dtos.user.UserShortDto;
import ru.practicum.ewm.models.user.User;

/**
 * Класс маппер ДТО пользователей
 *
 * @version 1.0
 * @autor Lobachev
 */
public class UserDtoMapper {
    /**
     * Метод - конвертировать User -> UserShortDto
     *
     * @param user - объект User
     */
    public static UserShortDto userShortDto(User user) {
        UserShortDto userDto = new UserShortDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        return userDto;
    }
}
