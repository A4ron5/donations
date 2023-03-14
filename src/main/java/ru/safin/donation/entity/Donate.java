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
@Table(name = "donates")
public class Donate extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @Column(precision = 10, scale = 2)
    @NotNull
    private BigDecimal sum;

    @Column(name = "donate_date", columnDefinition = "TIMESTAMP")
    @NotNull
    private LocalDateTime donateDate;

    @Column(columnDefinition = "TEXT")
    @NotNull
    private String text;

    @NotNull
    private String fromUser;

    @NotNull
    private Boolean fee_coverage;

    @NotNull
    private String status;
}
