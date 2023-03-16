package ru.safin.donation.validator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.safin.donation.exception.BusinessException;
import ru.safin.donation.service.DonateService;

@Component
@Slf4j
@RequiredArgsConstructor
public class DonateValidator {
    private final DonateService donateService;
    public void validateCallbackFromPaymentGateway(String hash, Long donateId) {
        var storedHash = donateService.getDonationHash(donateId);

        if (!storedHash.equals(hash)) {
            throw new BusinessException("Hashes does not match!");
        }
    }
}
