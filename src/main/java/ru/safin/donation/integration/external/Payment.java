package ru.safin.donation.integration.external;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
public class Payment {
    BigDecimal amount;
    String hash;
}
