package ru.safin.donation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private Long id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BigDecimal balance;
    private List<PayoutMethod> payoutMethods;
}
