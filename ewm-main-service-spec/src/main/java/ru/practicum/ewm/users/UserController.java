package ru.practicum.ewm.users;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.users.model.User;
import ru.practicum.ewm.users.service.UserServiceImpl;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/admin/users")
@RequiredArgsConstructor
@Valid
public class UserController {
    private final UserServiceImpl userService;

    @PostMapping
    public User createUser(@Validated @RequestBody User user) {

        return userService.createUser(user);
    }

    @GetMapping
    public List<User> getAllUsers(@RequestParam(required = false) List<Long> ids,
                                  @RequestParam(defaultValue = "0") Integer from,
                                  @RequestParam(defaultValue = "20") Integer size) {
        return userService.getAllUsers(ids, from, size);
    }

    @GetMapping(value = "{userId}")
    public User getUser(@PathVariable long userId) {
        return userService.getUser(userId);
    }

    @DeleteMapping(value = "{userId}")
    public void deleteUser(@PathVariable long userId) {
        userService.deleteUser(userId);
    }
}
