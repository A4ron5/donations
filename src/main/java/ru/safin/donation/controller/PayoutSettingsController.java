package ru.safin.donation.controller;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.safin.donation.dto.PayoutSettingsDto;
import ru.safin.donation.entity.PayoutSettings;
import ru.safin.donation.service.impl.PayoutSettingsServiceImpl;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PayoutSettingsController {
    public final PayoutSettingsServiceImpl payoutSettingsService;

    @GetMapping("/payout-settings/{userId}")
    public ResponseEntity<PayoutSettings> getPayoutSettings(@PathVariable @NotBlank Long userId) {
        var payoutSettings = payoutSettingsService.findPayoutSettingsByUserId(userId);

        return ResponseEntity.ok(payoutSettings);

    }

    @PutMapping("/payout-settings/{userId}")
    public ResponseEntity<PayoutSettingsDto> updatePayoutSettings(@PathVariable @NotBlank Long userId) {
        return ResponseEntity.ok(null);

    }
}
