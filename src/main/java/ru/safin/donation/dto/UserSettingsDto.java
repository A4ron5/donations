package ru.safin.donation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@Builder
@ToString
public class UserSettingsDto {
    @NotNull
    private Long id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BigDecimal percentFee;

    @Size(min = 3, max = 3)
    @NotBlank
    private String currency;

    @NotBlank
    @Size(min= 6)
    private String timezone;

    @NotBlank
    @Size(min = 2, max = 2)
    private String language;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String secretToken;
}
