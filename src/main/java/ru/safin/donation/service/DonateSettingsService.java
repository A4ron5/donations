package ru.safin.donation.service;

import ru.safin.donation.entity.DonateSettings;

public interface DonateSettingsService extends CommonCrudService<DonateSettings> {
    DonateSettings findDonateSettingsByUserId(Long userId);

    DonateSettings findUserAndUpdateSettings(Long userId, DonateSettings requestEntity);
}
