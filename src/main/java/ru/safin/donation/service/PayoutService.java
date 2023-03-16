package ru.safin.donation.service;

import ru.safin.donation.entity.Payout;

import java.util.List;

public interface PayoutService extends CommonCrudService<Payout> {
    List<Payout> findAllPayoutByUserId(Long userId);

    void withdrawPayout(Long userId, Long payoutMethodId, Payout request);
}
