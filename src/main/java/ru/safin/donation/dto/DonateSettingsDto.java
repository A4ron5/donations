package ru.safin.donation.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@Builder
@ToString
public class DonateSettingsDto {
    private BigDecimal minSum;
    private BigDecimal maxSum;
    private Integer maxCountOfSymbols;
    private Boolean isRemoveLinks;
    private Boolean isMessageModerate;
    private String blackList;

}
