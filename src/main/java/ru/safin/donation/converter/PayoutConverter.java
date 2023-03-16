package ru.safin.donation.converter;

import org.mapstruct.Mapper;
import ru.safin.donation.dto.PayoutDto;
import ru.safin.donation.entity.Payout;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PayoutConverter {

    PayoutDto toDtoPayout(Payout payout);

    Payout toEntityPayout(PayoutDto payoutDto);

    List<PayoutDto> toListOfPayoutDto(List<Payout> payouts);

}
