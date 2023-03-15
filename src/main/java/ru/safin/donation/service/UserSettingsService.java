package ru.safin.donation.service;

import ru.safin.donation.entity.UserSettings;

public interface UserSettingsService extends CommonCrudService<UserSettings>{
    UserSettings findUserSettingsByUserId(Long userId);

    UserSettings findUserAndUpdateSettings(Long userId, UserSettings requestEntity);
}
