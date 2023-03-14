package ru.safin.donation.controller;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.safin.donation.dto.UserSettingsDto;
import ru.safin.donation.entity.UserSettings;
import ru.safin.donation.service.impl.UserSettingsServiceImpl;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserSettingsController {

    private final UserSettingsServiceImpl userSettingsService;

    @GetMapping("/user-settings/{userId}")
    public ResponseEntity<UserSettings> getUserSettings(@PathVariable @NotBlank Long userId) {
        var userSettings = userSettingsService.getUserSettingsByUserId(userId);

        return ResponseEntity.ok(userSettings);

    }

    @PutMapping("/user-settings/{userId}")
    public ResponseEntity<UserSettingsDto> updateUserSettings(@PathVariable @NotBlank Long userId) {
        return ResponseEntity.ok(null);

    }

}
