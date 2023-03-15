package ru.safin.donation.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.safin.donation.converter.SettingsConverter;
import ru.safin.donation.dto.PayoutMethodDto;
import ru.safin.donation.dto.PayoutSettingsDto;
import ru.safin.donation.service.PayoutSettingsService;
import ru.safin.donation.validator.SettingsValidator;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payout-settings")
@RequiredArgsConstructor
public class PayoutSettingsController {
    private final PayoutSettingsService payoutSettingsService;
    private final SettingsConverter settingsConverter;

    private final SettingsValidator settingsValidator;

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

    @PostMapping("/{userId}/payoutMethod/{payoutSettingsId}")
    public ResponseEntity<List<PayoutMethodDto>> addPayoutMethod(
            @PathVariable @NotBlank Long userId,
            @PathVariable @NotBlank Long payoutSettingsId,
            @RequestBody PayoutMethodDto request
    ) {
        settingsValidator.validatePayoutMethodsInPayoutSettings(request, userId, payoutSettingsId);

        var requestEntity = settingsConverter.toEntityPayoutMethod(request);

        var storedEntity = payoutSettingsService.insertPayoutMethodToSettings(
                requestEntity,
                payoutSettingsId,
                userId
        );


        return ResponseEntity.ok(settingsConverter.toDtoPayoutMethod(storedEntity));

    }
}
