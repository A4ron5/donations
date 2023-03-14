package ru.safin.donation.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import ru.safin.donation.entity.PayoutMethod;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@ToString
public class PayoutSettingsDto {
    private BigDecimal balance;
    private List<PayoutMethod> payoutMethods;
}
