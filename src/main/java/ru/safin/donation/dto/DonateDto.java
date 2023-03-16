package ru.safin.donation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@ToString
public class DonateDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;

    @NotNull
    private BigDecimal sum;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @CreatedDate
    private LocalDateTime donateDate;

    @NotNull
    private String text;

    @NotNull
    private String fromUser;

    @NotNull
    private Boolean fee_coverage;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String status;
}
