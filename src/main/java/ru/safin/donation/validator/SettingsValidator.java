package ru.safin.donation.validator;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.safin.donation.configuration.DomainProperties;
import ru.safin.donation.dto.DonateSettingsDto;
import ru.safin.donation.dto.UserSettingsDto;
import ru.safin.donation.exception.BusinessException;

import java.math.BigDecimal;

@Component
@Slf4j
@RequiredArgsConstructor
public class SettingsValidator {

    private final DomainProperties domainProperties;

    public void validateDonationSettingsSum(DonateSettingsDto request) {
        BigDecimal minSum = request.getMinSum();
        BigDecimal maxSum = request.getMaxSum();

        if (minSum.compareTo(maxSum) > 0) {
            throw new BusinessException("maxSum must be greater then minSum in donation-settings");
        }
    }

    public void validateUserSettingsCurrencies(UserSettingsDto request) {
        List<String> availableCurrencies = domainProperties.getAvailableCurrencies();
        String requestCurrency = request.getCurrency();

        if (availableCurrencies.stream().noneMatch((availableCurrency) -> availableCurrency.equals(requestCurrency))) {
            throw new BusinessException("Not available currency in user-settings");
        }
    }
}
