package ru.safin.donation.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "user_settings")
public class UserSettings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @Column(precision = 5, scale = 2)
    @NotNull
    private BigDecimal percentFee;

    @NotNull
    @Size(min = 1, max = 3)
    private String currency;

    @NotNull
    @Size(max = 30)
    private String timezone;

    @NotNull
    @Size(max = 30)
    private String language;

    @NotNull
    private String secretToken;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "userSettings")
    private DonateSettings donateSettings;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "userSettings")
    private PayoutSettings payoutSettings;
}
