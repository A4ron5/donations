package ru.safin.donation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.safin.donation.dto.UserDto;
import ru.safin.donation.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping("/user")
    public UserDto createUser(@RequestBody UserDto request) {
        return request;
    }

    @GetMapping("/test/{id}")
    public void test(@PathVariable Long id) {
        userService.createDefaultUserEnvironment(id);
    }
}
