package ru.safin.donation.service;

import ru.safin.donation.entity.DonateSettings;

public interface DonateSettingsService extends CommonCrudService<DonateSettings> {
    public DonateSettings findDonateSettingsByUserId(Long userId);
}
