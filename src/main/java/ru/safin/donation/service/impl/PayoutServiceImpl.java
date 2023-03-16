package ru.safin.donation.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.safin.donation.dto.enums.PayoutStatus;
import ru.safin.donation.entity.Payout;
import ru.safin.donation.entity.PayoutMethod;
import ru.safin.donation.exception.BusinessException;
import ru.safin.donation.repository.PayoutRepository;
import ru.safin.donation.service.AbstractService;
import ru.safin.donation.service.PayoutService;
import ru.safin.donation.service.PayoutSettingsService;
import ru.safin.donation.service.UserService;

import java.util.List;

@Service
@Slf4j
public class PayoutServiceImpl extends AbstractService<Payout, PayoutRepository> implements PayoutService {

    private final PayoutSettingsService payoutSettingsService;
    private final UserService userService;

    public PayoutServiceImpl(
            PayoutRepository payoutRepository,
            PayoutSettingsService payoutSettingsService,
            UserService userService
    ) {
        super(payoutRepository);
        this.payoutSettingsService = payoutSettingsService;
        this.userService = userService;
    }
    @Override
    protected String getEntityName() {
        return "Payout";
    }


    @Override
    public List<Payout> findAllPayoutByUserId(Long userId) {
        return repository.findAllByUserId(userId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void withdrawPayout(Long userId, Long payoutMethodId, Payout request) {
        var payoutSettings = payoutSettingsService.findPayoutSettingsByUserId(userId);
        var user = userService.get(userId);
        request.setUser(user);

        var payoutMethod = getUserPayoutMethod(
                payoutSettings.getPayoutMethod(),
                payoutMethodId
        );
        request.setPayoutMethod(payoutMethod);

        var requestSum = request.getSum();
        var storedAvailableSum = payoutSettings.getBalance();

        if (storedAvailableSum.compareTo(requestSum) < 0) {
            throw new BusinessException("Not available payout sum for user balance");
        }

        var rest = storedAvailableSum.subtract(requestSum);

        payoutSettings.setBalance(rest);

        payoutSettingsService.update(payoutSettings);

        request.setStatus(PayoutStatus.DONE);
        request.setFee(payoutMethod.getFee());
        update(request);
    }

    private PayoutMethod getUserPayoutMethod(List<PayoutMethod> payoutMethods, Long currentPayoutMethodId) {
        return payoutMethods.stream()
                .filter((pm -> pm.getId().equals(currentPayoutMethodId))).findAny().orElse(null);
    }
}
