package ru.safin.donation.controller;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.safin.donation.dto.DonateSettingsDto;
import ru.safin.donation.entity.DonateSettings;
import ru.safin.donation.service.impl.DonateSettingsServiceImpl;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DonateSettingsController {
    public final DonateSettingsServiceImpl donateSettingsService;

    @GetMapping("/donate-settings/{userId}")
    public ResponseEntity<DonateSettings> getDonateSettings(@PathVariable @NotBlank Long userId) {
        var donateSettings = donateSettingsService.findDonateSettingsByUserId(userId);

        return ResponseEntity.ok(donateSettings);
    }

    @PutMapping("/donate-settings/{userId}")
    public ResponseEntity<DonateSettingsDto> updateDonateSettings(@PathVariable @NotBlank Long userId) {
        return ResponseEntity.ok(null);
    }
}
