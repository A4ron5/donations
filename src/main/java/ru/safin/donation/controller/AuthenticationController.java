package ru.safin.donation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import ru.safin.donation.security.CustomUser;
import ru.safin.donation.service.AuthenticationService;
import ru.safin.donation.service.UserService;

@RestController
@RequestMapping("/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;
    @GetMapping("/failure")
    public void failureAuth() {

    }

    @GetMapping("/success")
    public void successAuth(@AuthenticationPrincipal OAuth2User user) {
        log.info(user.toString());
        log.info("attributes={}", user.getAttributes().toString());
//        if (!authenticationService.checkIfUserAlreadyHasSettings(user.getEmail())) {
//            userService.createDefaultUserEnvironment(user);
//        }
    }
}
