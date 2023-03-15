package ru.safin.donation.validator;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.safin.donation.configuration.DomainProperties;
import ru.safin.donation.dto.DonateSettingsDto;
import ru.safin.donation.dto.PayoutMethodDto;
import ru.safin.donation.dto.UserSettingsDto;
import ru.safin.donation.entity.PayoutMethod;
import ru.safin.donation.entity.PayoutSettings;
import ru.safin.donation.exception.BusinessException;
import ru.safin.donation.service.PayoutSettingsService;

import java.math.BigDecimal;

@Component
@Slf4j
@RequiredArgsConstructor
public class SettingsValidator {

    private final DomainProperties domainProperties;
    private final PayoutSettingsService payoutSettingsService;

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

    public void validatePayoutMethodsInPayoutSettings(
            PayoutMethodDto request,
            Long userId,
            Long payoutSettingsId
    ) {
        int maxCountOfMethods = domainProperties.getMaxCountOfPayoutsMethods();
        PayoutSettings storedPayoutSettings = payoutSettingsService.get(payoutSettingsId);

        if (!storedPayoutSettings.getUser().getId().equals(userId)) {
            throw new BusinessException("Not valid payoutsSettings for user");
        }

        List<PayoutMethod> storedPayoutMethods = storedPayoutSettings.getPayoutMethod();

        if (storedPayoutMethods.size() > maxCountOfMethods) {
            throw new BusinessException("Too much count of payouts methods");
        }


        if (storedPayoutMethods.stream().anyMatch(PayoutMethod::getIs_main) && request.getIs_main()) {
            throw new BusinessException("Too much count of main payouts methods");
        }
    }
}
