package ru.safin.donation.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.safin.donation.entity.UserSettings;
import ru.safin.donation.repository.UserSettingsRepository;
import ru.safin.donation.service.AbstractService;
import ru.safin.donation.service.UserService;
import ru.safin.donation.service.UserSettingsService;

@Service
@Slf4j
public class UserSettingsServiceImpl extends AbstractService<UserSettings, UserSettingsRepository> implements UserSettingsService {
    private final UserService userService;

    public UserSettingsServiceImpl(
            UserSettingsRepository userSettingsRepository,
            UserService userService
    ) {
        super(userSettingsRepository);
        this.userService = userService;
    }

    @Override
    protected String getEntityName() {
        return "UserSettings";
    }

    @Override
    public UserSettings findUserSettingsByUserId(Long id) {
        return repository.findUserSettingsByUser_Id(id);
    }

    @Override
    public UserSettings findUserAndUpdateSettings(Long userId, UserSettings requestEntity) {
        var user = userService.get(userId);
        var storedUserSettings = findUserSettingsByUserId(userId);

        requestEntity.setUser(user);
        requestEntity.setSecretToken(storedUserSettings.getSecretToken());
        requestEntity.setPercentFee(storedUserSettings.getPercentFee());

        return update(requestEntity);
    }
}
