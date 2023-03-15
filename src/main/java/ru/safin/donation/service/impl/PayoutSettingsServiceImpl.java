package ru.safin.donation.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.safin.donation.entity.PayoutSettings;
import ru.safin.donation.repository.PayoutSettingsRepository;
import ru.safin.donation.service.AbstractService;
import ru.safin.donation.service.PayoutSettingsService;
import ru.safin.donation.service.UserService;

@Service
@Slf4j
public class PayoutSettingsServiceImpl extends AbstractService<PayoutSettings, PayoutSettingsRepository> implements PayoutSettingsService {
    private final UserService userService;

    public PayoutSettingsServiceImpl(
        PayoutSettingsRepository payoutSettingsRepository,
        UserService userService
    ) {
        super(payoutSettingsRepository);
        this.userService = userService;
    }

    @Override
    protected String getEntityName() {
        return "PayoutSettings";
    }

    @Override
    public PayoutSettings findPayoutSettingsByUserId(Long userId) {
        return repository.findPayoutSettingsByUser_Id(userId);
    }

    @Override
    public PayoutSettings findUserAndUpdateSettings(Long userId, PayoutSettings requestEntity) {
        var user = userService.get(userId);

        requestEntity.setUser(user);

        return update(requestEntity);
    }
}
