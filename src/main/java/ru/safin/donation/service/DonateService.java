package ru.safin.donation.service;


import ru.safin.donation.dto.enums.DonateStatus;
import ru.safin.donation.entity.Donate;

import java.util.List;

public interface DonateService extends CommonCrudService<Donate> {
    void depositDonate(Long donateId);

    DonateStatus createDonateToUser(Long userId, Donate donate);

    String getDonationHash(Long donateId);

    List<Donate> getAllDonatesByUserId(Long userId);

    List<Donate> findUserDonatesFromUserName(Long userId, String fromUser);
}
