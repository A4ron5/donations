package ru.safin.donation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import ru.safin.donation.entity.PayoutMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@ToString
public class PayoutDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private PayoutMethod payoutMethod;

    @NotNull
    private BigDecimal sum;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @CreatedDate
    private LocalDateTime payoutDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BigDecimal fee;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String status;
}
