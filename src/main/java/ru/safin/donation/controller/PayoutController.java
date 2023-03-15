package ru.safin.donation.controller;

import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;
import ru.safin.donation.entity.Payout;

@RestController
@RequestMapping("/api/v1/payouts")
public class PayoutController {

    @GetMapping("/{userId}")
    public void getUserPayouts(@PathVariable @NotBlank Long userId) {

    }

    @PostMapping("/{userId}")
    public void makePayouts(@PathVariable @NotBlank Long userId, @RequestBody Payout payout) {

    }
}
