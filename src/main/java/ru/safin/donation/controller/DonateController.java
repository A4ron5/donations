package ru.safin.donation.controller;

import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;
import ru.safin.donation.entity.Donate;

@RestController
@RequestMapping("/api/v1/donates")
public class DonateController {

    @GetMapping("/{userId}")
    public void getUserDonates(@PathVariable @NotBlank Long userId) {

    }

    @PostMapping("/{userId}")
    public void makeDonateToUser(@PathVariable @NotBlank Long userId, @RequestBody Donate donate) {

    }
}
