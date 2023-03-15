package ru.safin.donation.service;

import ru.safin.donation.entity.PayoutSettings;

public interface PayoutSettingsService extends CommonCrudService<PayoutSettings>{
    PayoutSettings findPayoutSettingsByUserId(Long userId);

    PayoutSettings findUserAndUpdateSettings(Long userId, PayoutSettings requestEntity);
}
