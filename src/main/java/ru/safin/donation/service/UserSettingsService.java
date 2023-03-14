package ru.safin.donation.service;

import ru.safin.donation.entity.UserSettings;
import ru.safin.donation.repository.UserSettingsRepository;

public interface UserSettingsService extends CommonCrudService<UserSettings>{

    public UserSettings getUserSettingsByUserId(Long userId);
}
