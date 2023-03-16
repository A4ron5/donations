package ru.safin.donation.converter;

import org.mapstruct.Mapper;
import ru.safin.donation.dto.DonateDto;
import ru.safin.donation.entity.Donate;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DonateConverter {
    DonateDto toDtoDonate(Donate donate);

    Donate toEntityDonate(DonateDto donateDto);

    List<DonateDto> toListOfDonateDto(List<Donate> donates);
}
