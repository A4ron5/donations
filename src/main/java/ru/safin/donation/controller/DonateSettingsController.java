package ru.safin.donation.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.safin.donation.converter.SettingsConverter;
import ru.safin.donation.dto.DonateSettingsDto;
import ru.safin.donation.service.DonateSettingsService;
import ru.safin.donation.validator.SettingsValidator;

@RestController
@RequestMapping("/api/v1/donate-settings")
@RequiredArgsConstructor
public class DonateSettingsController {
    private final DonateSettingsService donateSettingsService;
    private final SettingsConverter settingsConverter;

    private final SettingsValidator settingsValidator;

    @GetMapping("/{userId}")
    public ResponseEntity<DonateSettingsDto> getDonateSettings(@PathVariable @NotBlank Long userId) {
        var donateSettings = donateSettingsService.findDonateSettingsByUserId(userId);

        return ResponseEntity.ok(settingsConverter.toDtoDonateSettings(donateSettings));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<DonateSettingsDto> updateDonateSettings(
            @PathVariable @NotBlank Long userId,
            @RequestBody @Valid DonateSettingsDto request
    ) {
        settingsValidator.validateDonationSettingsSum(request);

        var requestEntity = settingsConverter.toEntityDonateSettings(request);
        var storedEntity = donateSettingsService.findUserAndUpdateSettings(userId, requestEntity);

        return ResponseEntity.ok(settingsConverter.toDtoDonateSettings(storedEntity));
    }
}
