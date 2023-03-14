package ru.safin.donation.converter;

import org.mapstruct.Mapper;
import ru.safin.donation.dto.DonateSettingsDto;
import ru.safin.donation.dto.PayoutSettingsDto;
import ru.safin.donation.dto.UserSettingsDto;
import ru.safin.donation.entity.DonateSettings;
import ru.safin.donation.entity.PayoutSettings;
import ru.safin.donation.entity.UserSettings;

@Mapper
public interface SettingsConverter {
    DonateSettingsDto toDtoDonateSettings(DonateSettings donateSettings);
    DonateSettings toEntityDonateSettings(DonateSettingsDto donateSettingsDto);

    PayoutSettingsDto toDtoPayoutSettings(PayoutSettings payoutSettings);
    PayoutSettings toEntityPayoutSettings(PayoutSettingsDto payoutSettingsDto);

    UserSettingsDto toDtoUserSettings(UserSettings userSettings);

    UserSettings toEntityUserSettings(UserSettingsDto userSettingsDto);

}
