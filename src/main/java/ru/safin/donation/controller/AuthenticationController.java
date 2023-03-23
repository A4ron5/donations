package ru.safin.donation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import ru.safin.donation.security.CustomUser;
import org.springframework.web.servlet.view.RedirectView;
import ru.safin.donation.entity.User;
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
    public RedirectView successAuth(@AuthenticationPrincipal OAuth2User user) {
        log.info(user.toString());

        String email = user.getAttribute("email");
        String login = user.getAttribute("login");
        String avatar_url = user.getAttribute("avatar_url");

        User entityUser = new User();
        entityUser.setEmail(email);
        entityUser.setNickname(login);
        entityUser.setLink_picture(avatar_url);

        Long userId = 0L;

        if (!authenticationService.checkIfUserAlreadyHasSettings(email)) {
            userId = userService.createDefaultUserEnvironment(entityUser);
        } else {
            userId = userService.findByEmail(email).getId();
        }

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:3000/?userId=" + userId.toString());
        return redirectView;
    }
}
