package ru.safin.donation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/donation-page")
public class DonationPageController {

    @PostMapping("/donate")
    public ResponseEntity<?> createDonate() {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/donates/{id}")
    public ResponseEntity<?> getDonateHistory(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(null);
    }
}
