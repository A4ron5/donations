package ru.safin.donation.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.safin.donation.entity.DonateSettings;
import ru.safin.donation.repository.DonateSettingsRepository;
import ru.safin.donation.service.AbstractService;
import ru.safin.donation.service.DonateSettingsService;
import ru.safin.donation.service.UserService;

@Service
@Slf4j
public class DonateSettingsServiceImpl extends AbstractService<DonateSettings, DonateSettingsRepository> implements DonateSettingsService {
    private final UserService userService;

    public DonateSettingsServiceImpl(
            DonateSettingsRepository donateSettingsRepository,
            UserService userService
    ) {
        super(donateSettingsRepository);
        this.userService = userService;
    }

    @Override
    protected String getEntityName() {
        return "DonateSettings";
    }

    @Override
    public DonateSettings findDonateSettingsByUserId(Long userId) {
        return repository.findDonateSettingsByUser_Id(userId);
    }

    @Override
    public DonateSettings findUserAndUpdateSettings(Long userId, DonateSettings requestEntity) {
        var user = userService.get(userId);

        requestEntity.setUser(user);

        return update(requestEntity);
    }

}
