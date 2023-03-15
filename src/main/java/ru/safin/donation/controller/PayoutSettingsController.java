package ru.safin.donation.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.safin.donation.converter.SettingsConverter;
import ru.safin.donation.dto.PayoutSettingsDto;
import ru.safin.donation.service.PayoutSettingsService;

@RestController
@RequestMapping("/api/v1/payout-settings")
@RequiredArgsConstructor
public class PayoutSettingsController {
    public final PayoutSettingsService payoutSettingsService;
    public final SettingsConverter settingsConverter;

    @GetMapping("/{userId}")
    public ResponseEntity<PayoutSettingsDto> getPayoutSettings(@PathVariable @NotBlank Long userId) {
        var payoutSettings = payoutSettingsService.findPayoutSettingsByUserId(userId);

        return ResponseEntity.ok(settingsConverter.toDtoPayoutSettings(payoutSettings));

    }

    @PutMapping("/{userId}")
    public ResponseEntity<PayoutSettingsDto> updatePayoutSettings(
            @PathVariable @NotBlank Long userId,
            @RequestBody @Valid PayoutSettingsDto request
    ) {
        var requestEntity = settingsConverter.toEntityPayoutSettings(request);
        var storedEntity = payoutSettingsService.findUserAndUpdateSettings(userId, requestEntity);

        return ResponseEntity.ok(settingsConverter.toDtoPayoutSettings(storedEntity));

    }
}
