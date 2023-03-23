package ru.safin.donation.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.safin.donation.configuration.DomainProperties;
import ru.safin.donation.entity.DonateSettings;
import ru.safin.donation.entity.PayoutSettings;
import ru.safin.donation.entity.User;
import ru.safin.donation.entity.UserSettings;
import ru.safin.donation.repository.DonateSettingsRepository;
import ru.safin.donation.repository.PayoutSettingsRepository;
import ru.safin.donation.repository.UserRepository;
import ru.safin.donation.repository.UserSettingsRepository;
//import ru.safin.donation.security.CustomUser;
import ru.safin.donation.service.AbstractService;
import ru.safin.donation.service.UserService;
import ru.safin.donation.utils.TokenGenerator;

import java.util.Random;

@Service
@Slf4j
public class UserServiceImpl extends AbstractService<User, UserRepository> implements UserService {
    private final UserSettingsRepository userSettingsRepository;
    private final DonateSettingsRepository donateSettingsRepository;
    private final PayoutSettingsRepository payoutSettingsRepository;
    private final DomainProperties domainProperties;

    public UserServiceImpl(
            UserRepository userRepository,
            UserSettingsRepository userSettingsRepository,
            DonateSettingsRepository donateSettingsRepository,
            PayoutSettingsRepository payoutSettingsRepository,
            DomainProperties domainProperties
    ) {
        super(userRepository);
        this.userSettingsRepository = userSettingsRepository;
        this.donateSettingsRepository = donateSettingsRepository;
        this.payoutSettingsRepository = payoutSettingsRepository;
        this.domainProperties = domainProperties;
    }

    @Override
    public Long createDefaultUserEnvironment(User user) {
        var storedUser = create(user);
        log.info("Creating default environment for user with id={}", storedUser.getId());


        var userSettings = createDefaultUserSettings(storedUser);
        var donateSettings = createDefaultDonateSettings(storedUser);
        var payoutSettings = createDefaultPayoutSettings(storedUser);

        userSettingsRepository.save(userSettings);
        donateSettingsRepository.save(donateSettings);
        payoutSettingsRepository.save(payoutSettings);

        return storedUser.getId();
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    private UserSettings createDefaultUserSettings(User user) {
        log.info("Creating default user settings for user={}", user);

        var userSettings = new UserSettings();
        var defaultUserSettings = domainProperties.getUserSettings();

        userSettings.setUser(user);
        userSettings.setCurrency(defaultUserSettings.getCurrency());
        userSettings.setLanguage(defaultUserSettings.getLanguage());
        userSettings.setTimezone(defaultUserSettings.getTimezone());
        userSettings.setPercentFee(defaultUserSettings.getPercentFee());
        userSettings.setSecretToken(TokenGenerator.generateToken());

        return userSettings;
    }

    private DonateSettings createDefaultDonateSettings(User user) {
        log.info("Creating default donate settings for user={}", user);

        var donateSettings = new DonateSettings();
        var defaultDonateSettings = domainProperties.getDonationSettings();

        donateSettings.setUser(user);
        donateSettings.setIsRemoveLinks(defaultDonateSettings.getIsRemoveLinks());
        donateSettings.setMaxCountOfSymbols(defaultDonateSettings.getMaxCountOfSymbols());
        donateSettings.setMinSum(defaultDonateSettings.getMinSum());
        donateSettings.setMaxSum(defaultDonateSettings.getMaxSum());
        donateSettings.setIsMessageModerate(defaultDonateSettings.getIsMessageModerate());

        return donateSettings;
    }

    private PayoutSettings createDefaultPayoutSettings(User user) {
        log.info("Creating default payout settings for user={}", user);

        var payoutSettings = new PayoutSettings();
        var defaultPayoutSettings = domainProperties.getPayoutsSettings();

        payoutSettings.setUser(user);
        payoutSettings.setBalance(defaultPayoutSettings.getBalance());

        return payoutSettings;
    }

    @Override
    protected String getEntityName() {
        return "User";
    }
}
