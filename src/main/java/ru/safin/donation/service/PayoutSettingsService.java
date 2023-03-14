package ru.safin.donation.service;

import ru.safin.donation.entity.PayoutSettings;

public interface PayoutSettingsService extends CommonCrudService<PayoutSettings>{
    public PayoutSettings findPayoutSettingsByUserId(Long userId);
}
