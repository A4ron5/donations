package ru.safin.donation.controller;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import ru.safin.donation.dto.UserDto;
import ru.safin.donation.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping("/callback/twitch")
    public void callbackTwitch(
            @RequestParam @NotBlank String access_token,
            @RequestParam String scope,
            @RequestParam String token_type
    ) {
        log.info("access_token={}, scope={}, token_type={}", access_token, scope, token_type);
    }

    @GetMapping("/me")
    public void getUser(@AuthenticationPrincipal OAuth2User user) {
        log.info(user.toString());
    }
}
