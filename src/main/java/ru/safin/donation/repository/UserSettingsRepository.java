package ru.safin.donation.repository;

import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Repository;
import ru.safin.donation.entity.User;
import ru.safin.donation.entity.UserSettings;

@Repository
public interface UserSettingsRepository extends CommonRepository<UserSettings>{
    public UserSettings findUserSettingsByUser_Id(Long id);
}
