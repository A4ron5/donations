package ru.safin.donation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payouts")
public class DonationPayoutsController {


    @PostMapping("/methods/{id}")
    public ResponseEntity<?> addPayoutsMethods(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/methods/{id}")
    public ResponseEntity<?> removePayoutsMethods(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/methods/{id}")
    public ResponseEntity<?> getPayoutsMethods(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/history/{id}")
    public ResponseEntity<?> getPayoutsHistory(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(null);
    }
}
