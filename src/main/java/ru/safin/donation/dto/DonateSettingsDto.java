package ru.safin.donation.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@Builder
@ToString
public class DonateSettingsDto {
    @NotNull
    private Long id;
    @NotNull
    @DecimalMin("10")
    private BigDecimal minSum;
    @NotNull
    @DecimalMax("50000")
    private BigDecimal maxSum;
    @NotNull
    @Max(250)
    private Integer maxCountOfSymbols;
    @NotNull
    private Boolean isRemoveLinks;
    @NotNull
    private Boolean isMessageModerate;
    private String blackList;

}
