package ru.safin.donation.service.impl;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.safin.donation.dto.enums.DonateStatus;
import ru.safin.donation.entity.Donate;
import ru.safin.donation.exception.BusinessException;
import ru.safin.donation.integration.external.Payment;
import ru.safin.donation.integration.external.PaymentGateway;
import ru.safin.donation.repository.DonateRepository;
import ru.safin.donation.service.AbstractService;
import ru.safin.donation.service.DonateService;
import ru.safin.donation.service.PayoutSettingsService;
import ru.safin.donation.service.UserService;
import ru.safin.donation.utils.TokenGenerator;

@Service
@Slf4j
public class DonateServiceImpl extends AbstractService<Donate, DonateRepository> implements DonateService {
    private final UserService userService;
    private final PaymentGateway paymentGateway;
    private final PayoutSettingsService payoutSettingsService;
    public DonateServiceImpl(
            DonateRepository donateRepository,
            UserService userService,
            PaymentGateway paymentGateway,
            PayoutSettingsService payoutSettingsService
    ) {
        super(donateRepository);
        this.userService = userService;
        this.paymentGateway = paymentGateway;
        this.payoutSettingsService = payoutSettingsService;
    }
    @Override
    protected String getEntityName() {
        return "Donate";
    }


    //SYNC
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Override
    public void depositDonate(Long donateId) {
        var storedDonate = get(donateId);

        if (!storedDonate.getStatus().equals(DonateStatus.CREATED)) {
            throw new BusinessException("Donate status does not match");
        }


        payoutSettingsService.updateUserBalance(storedDonate);

        storedDonate.setStatus(DonateStatus.DONE);
        update(storedDonate);
    }

    @Override
    public DonateStatus createDonateToUser(Long userId, Donate donate) {
        var user = userService.get(userId);

        donate.setUser(user);
        donate.setStatus(DonateStatus.CREATED);

        var hash = TokenGenerator.generateToken();
        donate.setHash(hash);

        repository.save(donate);

        paymentGateway.processToPayment(
                Payment.builder()
                        .hash(hash)
                        .amount(donate.getSum())
                        .build()
        );

        return DonateStatus.CREATED;
    }

    @Override
    public String getDonationHash(Long donateId) {
        return get(donateId).getHash();
    }

    public List<Donate> getAllDonatesByUserId(Long userId) {
        return repository.findAllByUserId(userId);
    }

    @Override
    public List<Donate> findUserDonatesFromUserName(Long userId, String fromUser) {
        return repository.findAllByUserIdAndFromUser(userId, fromUser);
    }
}
