package ru.practicum.ewm.users.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.OffsetLimitPageable;
import ru.practicum.ewm.exception.ExceptionNotFound;
import ru.practicum.ewm.users.UserRepository;
import ru.practicum.ewm.users.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers(List<Long> ids, Integer from, Integer size) {
        Pageable pageable = OffsetLimitPageable.of(from, size, Sort.by("id"));
        if (ids == null) {
            return userRepository.findAll(pageable).stream().collect(Collectors.toList());
        }
        return userRepository.findAll(ids, pageable);
    }

    public User getUser(long userId) {
        User user = userRepository.findById(userId).orElse(new User());
        if (user.getName() == null) {
            throw new ExceptionNotFound("Пользователь не найден");
        }
        return user;
    }

    public void deleteUser(long userId) {
        getUser(userId);
        userRepository.deleteById(userId);
    }
}
