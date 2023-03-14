package ru.safin.donation.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.safin.donation.entity.PayoutSettings;
import ru.safin.donation.repository.PayoutSettingsRepository;
import ru.safin.donation.service.AbstractService;
import ru.safin.donation.service.PayoutSettingsService;

@Service
@Slf4j
public class PayoutSettingsServiceImpl extends AbstractService<PayoutSettings, PayoutSettingsRepository> implements PayoutSettingsService {

    public PayoutSettingsServiceImpl(
        PayoutSettingsRepository payoutSettingsRepository
    ) {
        super(payoutSettingsRepository);
    }

    @Override
    protected String getEntityName() {
        return "PayoutSettings";
    }

    @Override
    public PayoutSettings findPayoutSettingsByUserId(Long userId) {
        return repository.findPayoutSettingsByUser_Id(userId);
    }
}
