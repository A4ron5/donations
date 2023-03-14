package ru.safin.donation.repository;

import org.springframework.stereotype.Repository;
import ru.safin.donation.entity.DonateSettings;

@Repository
public interface DonateSettingsRepository extends CommonRepository<DonateSettings> {
    public DonateSettings findDonateSettingsByUser_Id(Long id);

}
