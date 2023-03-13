package ru.safin.donation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/donation-settings")
public class DonationSettingsController {

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserSettings(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(id);
    }


    @PutMapping("/user/{id}")
    public ResponseEntity<?> updateMessagesSettings(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(null);
    }
}
