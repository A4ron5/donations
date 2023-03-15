package ru.safin.donation.converter;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.safin.donation.dto.DonateSettingsDto;
import ru.safin.donation.dto.PayoutMethodDto;
import ru.safin.donation.dto.PayoutSettingsDto;
import ru.safin.donation.dto.UserSettingsDto;
import ru.safin.donation.entity.DonateSettings;
import ru.safin.donation.entity.PayoutMethod;
import ru.safin.donation.entity.PayoutSettings;
import ru.safin.donation.entity.UserSettings;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface SettingsConverter {
    DonateSettingsDto toDtoDonateSettings(DonateSettings donateSettings);
    DonateSettings toEntityDonateSettings(DonateSettingsDto donateSettingsDto);

    PayoutSettingsDto innnerToDtoPayoutSettings(PayoutSettings payoutSettings);

    default PayoutSettingsDto toDtoPayoutSettings(PayoutSettings payoutSettings) {
        List<PayoutMethod> sourcePayoutMethods = payoutSettings.getPayoutMethod();
        List<PayoutMethodDto> targetPayoutMethods = sourcePayoutMethods.stream().map(this::toDtoPayoutMethod).toList();


        var target = innnerToDtoPayoutSettings(payoutSettings);
        target.setPayoutMethods(targetPayoutMethods);;

        return target;
    }

    PayoutSettings toEntityPayoutSettings(PayoutSettingsDto payoutSettingsDto);

    UserSettingsDto toDtoUserSettings(UserSettings userSettings);

    UserSettings toEntityUserSettings(UserSettingsDto userSettingsDto);

    PayoutMethod toEntityPayoutMethod(PayoutMethodDto payoutMethodDto);

    List<PayoutMethodDto> toDtoPayoutMethodList(List<PayoutMethod> payoutMethodList);

    PayoutMethodDto toDtoPayoutMethod(PayoutMethod payoutMethod);
}
