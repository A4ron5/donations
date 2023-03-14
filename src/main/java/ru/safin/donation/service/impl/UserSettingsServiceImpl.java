package ru.safin.donation.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.safin.donation.entity.UserSettings;
import ru.safin.donation.repository.UserSettingsRepository;
import ru.safin.donation.service.AbstractService;
import ru.safin.donation.service.UserSettingsService;

@Service
@Slf4j
public class UserSettingsServiceImpl extends AbstractService<UserSettings, UserSettingsRepository> implements UserSettingsService {

    public UserSettingsServiceImpl(
            UserSettingsRepository userSettingsRepository
    ) {
        super(userSettingsRepository);
    }


    @Override
    protected String getEntityName() {
        return "UserSettings";
    }

    @Override
    public UserSettings getUserSettingsByUserId(Long id) {
        return repository.findUserSettingsByUser_Id(id);
    }
}
