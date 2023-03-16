package ru.safin.donation.controller;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.safin.donation.converter.DonateConverter;
import ru.safin.donation.dto.DonateDto;
import ru.safin.donation.dto.enums.DonateStatus;
import ru.safin.donation.service.DonateService;
import ru.safin.donation.validator.DonateValidator;

import java.util.List;

@RestController
@RequestMapping("/api/v1/donates")
@RequiredArgsConstructor
public class DonateController {

    private final DonateValidator donateValidator;
    private final DonateConverter donateConverter;
    private final DonateService donateService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<DonateDto>> getUserDonates(@PathVariable @NotBlank Long userId) {
        var storedEntities = donateService.getAllDonatesByUserId(userId);

        return ResponseEntity.ok(donateConverter.toListOfDonateDto(storedEntities));
    }

    @GetMapping("/{userId}/from/{fromUser}")
    public ResponseEntity<List<DonateDto>> findUserDonatesFromUser(
            @PathVariable @NotBlank Long userId,
            @PathVariable @NotBlank String fromUser
    ) {
        var storedEntities = donateService.findUserDonatesFromUserName(userId, fromUser);

        return ResponseEntity.ok(donateConverter.toListOfDonateDto(storedEntities));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<DonateStatus> createDonateToUser(@PathVariable @NotBlank Long userId, @RequestBody DonateDto request) {
        var requestEntity = donateConverter.toEntityDonate(request);

        var donateStatus = donateService.createDonateToUser(userId, requestEntity);

        return ResponseEntity.ok(donateStatus);
    }

    @PostMapping("/callback/{donateId}/deposit/{hash}")
    public void callbackDonateDeposit(
            @PathVariable @NotBlank String hash,
            @PathVariable @NotBlank Long donateId
    ) {
        donateValidator.validateCallbackFromPaymentGateway(hash, donateId);


        donateService.depositDonate(donateId);

    }
}
