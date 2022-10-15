package ru.practicum.ewm.users.service;

import ru.practicum.ewm.users.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    List<User> getAllUsers(List<Long> ids, Integer from, Integer size);

    User getUser(long userId);

    void deleteUser(long userId);
}
