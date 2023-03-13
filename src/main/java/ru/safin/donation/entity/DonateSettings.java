package ru.safin.donation.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "donate_settings")
public class DonateSettings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_settings_id")
    @NotNull
    private UserSettings userSettings;

    @Column(precision = 10, scale = 2)
    @NotNull
    private BigDecimal  minSum;

    @Column(precision = 10, scale = 2)
    @NotNull
    private BigDecimal maxSum;

    @NotNull
    @Min(10)
    private Integer maxCountOfSymbols;

    @NotNull
    private Boolean isRemoveLinks;

    @NotNull
    private Boolean isMessageModerate;

    private String blackList;
}
