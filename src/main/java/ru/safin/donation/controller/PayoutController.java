package ru.safin.donation.controller;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.safin.donation.converter.PayoutConverter;
import ru.safin.donation.dto.PayoutDto;
import ru.safin.donation.entity.Payout;
import ru.safin.donation.service.PayoutService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payouts")
@RequiredArgsConstructor
public class PayoutController {
    private final PayoutService payoutService;
    private final PayoutConverter payoutConverter;

    @GetMapping("/{userId}")
    public ResponseEntity<List<PayoutDto>> getUserPayouts(@PathVariable @NotBlank Long userId) {
        var storedEntities = payoutService.findAllPayoutByUserId(userId);

        return ResponseEntity.ok(payoutConverter.toListOfPayoutDto(storedEntities));
    }

    @PostMapping("/withdraw/{userId}/{payoutMethodId}")
    public void makePayouts(
            @PathVariable @NotBlank Long userId,
            @PathVariable @NotBlank Long payoutMethodId,
            @RequestBody PayoutDto request
    ) {
        var requestEntity = payoutConverter.toEntityPayout(request);

        payoutService.withdrawPayout(userId, payoutMethodId, requestEntity);
    }
}
