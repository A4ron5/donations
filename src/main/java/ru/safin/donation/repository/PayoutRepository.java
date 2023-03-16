package ru.safin.donation.repository;

import ru.safin.donation.entity.Payout;

import java.util.List;

public interface PayoutRepository extends CommonRepository<Payout> {
    List<Payout> findAllByUserId(Long userId);

}
