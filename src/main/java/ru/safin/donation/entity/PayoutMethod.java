package ru.safin.donation.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "payout_methods")
public class PayoutMethod extends AbstractEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payout_settings_id")
    private PayoutSettings payoutSettings;

    @NotNull
    private Boolean is_main;

    @NotNull
    private String cardNumber;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    @NotNull
    private LocalDateTime createdAt;

    @Column(name = "modified_at", columnDefinition = "TIMESTAMP")
    @NotNull
    private LocalDateTime modifiedAt;

    @Column(precision = 10, scale = 2)
    @NotNull
    private BigDecimal fee;
}

