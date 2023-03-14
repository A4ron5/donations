package ru.safin.donation.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@Builder
@ToString
public class UserSettingsDto {
    private BigDecimal percentFee;
    private String currency;
    private String timezone;
    private String language;
    private String secretToken;
}
