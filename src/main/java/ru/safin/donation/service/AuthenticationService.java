package ru.safin.donation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.safin.donation.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;

    public boolean checkIfUserAlreadyHasSettings(String userEmail) {
        var user = userService.findByEmail(userEmail);

        return null != user;
    }
}
