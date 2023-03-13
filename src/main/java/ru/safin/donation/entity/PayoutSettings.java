package ru.safin.donation.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@Table(name = "payout_settings")
public class PayoutSettings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_settings_id")
    @NotNull
    private UserSettings userSettings;

    @Column(precision = 10, scale = 2)
    @NotNull
    private BigDecimal balance;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "payoutSettings")
    private List<PayoutMethod> payoutMethod;
}
