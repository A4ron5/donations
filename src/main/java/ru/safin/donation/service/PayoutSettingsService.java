package ru.safin.donation.service;

import ru.safin.donation.entity.PayoutMethod;
import ru.safin.donation.entity.PayoutSettings;

import java.util.List;

public interface PayoutSettingsService extends CommonCrudService<PayoutSettings>{
    PayoutSettings findPayoutSettingsByUserId(Long userId);

    PayoutSettings findUserAndUpdateSettings(Long userId, PayoutSettings requestEntity);

    List<PayoutMethod> insertPayoutMethodToSettings(
            PayoutMethod payoutMethod,
            Long payoutSettingsId,
            Long userId
    );

}
