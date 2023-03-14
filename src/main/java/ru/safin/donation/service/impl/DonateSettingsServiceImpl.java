package ru.safin.donation.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.safin.donation.entity.DonateSettings;
import ru.safin.donation.repository.DonateSettingsRepository;
import ru.safin.donation.service.AbstractService;
import ru.safin.donation.service.DonateSettingsService;

@Service
@Slf4j
public class DonateSettingsServiceImpl extends AbstractService<DonateSettings, DonateSettingsRepository> implements DonateSettingsService {

    public DonateSettingsServiceImpl(
            DonateSettingsRepository donateSettingsRepository
    ) {
        super(donateSettingsRepository);
    }

    @Override
    protected String getEntityName() {
        return "DonateSettings";
    }

    @Override
    public DonateSettings findDonateSettingsByUserId(Long userId) {
        return repository.findDonateSettingsByUser_Id(userId);
    }
}
