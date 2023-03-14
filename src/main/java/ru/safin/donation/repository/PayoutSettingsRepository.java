package ru.safin.donation.repository;

import org.springframework.stereotype.Repository;
import ru.safin.donation.entity.PayoutSettings;

@Repository
public interface PayoutSettingsRepository extends CommonRepository<PayoutSettings> {
    public PayoutSettings findPayoutSettingsByUser_Id(Long id);
}
