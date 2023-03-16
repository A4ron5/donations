package ru.safin.donation.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.safin.donation.configuration.DomainProperties;
import ru.safin.donation.entity.Donate;
import ru.safin.donation.entity.PayoutMethod;
import ru.safin.donation.entity.PayoutSettings;
import ru.safin.donation.exception.BusinessException;
import ru.safin.donation.repository.PayoutMethodRepository;
import ru.safin.donation.repository.PayoutSettingsRepository;
import ru.safin.donation.service.AbstractService;
import ru.safin.donation.service.PayoutSettingsService;
import ru.safin.donation.service.UserService;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class PayoutSettingsServiceImpl extends AbstractService<PayoutSettings, PayoutSettingsRepository> implements PayoutSettingsService {
    private final UserService userService;
    private final DomainProperties domainProperties;
    private final PayoutMethodRepository payoutMethodRepository;

    public PayoutSettingsServiceImpl(
        PayoutSettingsRepository payoutSettingsRepository,
        UserService userService,
        DomainProperties domainProperties,
        PayoutMethodRepository payoutMethodRepository
    ) {
        super(payoutSettingsRepository);
        this.userService = userService;
        this.domainProperties = domainProperties;
        this.payoutMethodRepository = payoutMethodRepository;
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

    @Override
    public List<PayoutMethod> insertPayoutMethodToSettings(
            PayoutMethod payoutMethod,
            Long payoutSettingsId,
            Long userId
    ) {
        var storedPayoutSettings = get(payoutSettingsId);

        payoutMethod.setFee(domainProperties.getCardFee());
        payoutMethod.setPayoutSettings(storedPayoutSettings);
        payoutMethodRepository.save(payoutMethod);

        List<PayoutMethod> updatedPayoutMethod = storedPayoutSettings.getPayoutMethod();
        updatedPayoutMethod.add(payoutMethod);
        storedPayoutSettings.setPayoutMethod(updatedPayoutMethod);

        return update(storedPayoutSettings).getPayoutMethod();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUserBalance(Donate donate) {
        var storedPayoutSettings = findPayoutSettingsByUserId(donate.getUser().getId());

        BigDecimal amount = donate.getSum();
        BigDecimal storedAmount = storedPayoutSettings.getBalance();

        storedPayoutSettings.setBalance(storedAmount.add(amount));

        update(storedPayoutSettings);
    }

}
