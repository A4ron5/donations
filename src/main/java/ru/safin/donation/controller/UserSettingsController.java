package ru.safin.donation.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.safin.donation.converter.SettingsConverter;
import ru.safin.donation.dto.UserSettingsDto;
import ru.safin.donation.service.UserSettingsService;
import ru.safin.donation.validator.SettingsValidator;

@RestController
@RequestMapping("/api/v1/user-settings")
@RequiredArgsConstructor
public class UserSettingsController {
    public final UserSettingsService userSettingsService;
    public final SettingsConverter settingsConverter;
    public final SettingsValidator settingsValidator;

    @GetMapping("/{userId}")
    public ResponseEntity<UserSettingsDto> getUserSettings(@PathVariable @NotBlank Long userId) {
        var userSettings = userSettingsService.findUserSettingsByUserId(userId);

        return ResponseEntity.ok(settingsConverter.toDtoUserSettings(userSettings));

    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserSettingsDto> updateUserSettings(
            @PathVariable @NotBlank Long userId,
            @RequestBody @Valid UserSettingsDto request
    ) {
        settingsValidator.validateUserSettingsCurrencies(request);

        var requestEntity = settingsConverter.toEntityUserSettings(request);
        var storedEntity = userSettingsService.findUserAndUpdateSettings(userId, requestEntity);

        return ResponseEntity.ok(settingsConverter.toDtoUserSettings(storedEntity));

    }

}
