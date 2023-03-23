package ru.safin.donation.controller;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import ru.safin.donation.dto.UserDto;
import ru.safin.donation.entity.User;
import ru.safin.donation.service.UserService;
import ru.safin.donation.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;

    @GetMapping("/me/{id}")
    public ResponseEntity<User> getUser(
            @PathVariable @NotBlank Long id
    ) {
        return ResponseEntity.ok(userService.get(id));
    }
}
