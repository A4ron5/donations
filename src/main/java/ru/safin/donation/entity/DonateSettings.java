package ru.safin.donation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "donate_settings")
public class DonateSettings extends AbstractEntity {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotNull
    @JsonIgnore
    private User user;

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
