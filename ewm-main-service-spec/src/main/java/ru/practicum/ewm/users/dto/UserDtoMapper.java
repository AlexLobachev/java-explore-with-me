package ru.practicum.ewm.users.dto;

import ru.practicum.ewm.users.model.User;

public class UserDtoMapper {
    public static UserShortDto userShortDto(User user) {
        UserShortDto userDto = new UserShortDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        return userDto;
    }
}
