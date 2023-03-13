package ru.safin.donation.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "payouts")
public class Payout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "payout_method_id")
    @NotNull
    private PayoutMethod payoutMethod;

    @Column(precision = 10, scale = 2)
    @NotNull
    private BigDecimal sum;

    @Column(name = "payout_date", columnDefinition = "TIMESTAMP")
    @NotNull
    private LocalDateTime payoutDate;

    @Column(precision = 10, scale = 2)
    @NotNull
    private BigDecimal fee;

    @NotNull
    @Size(min = 2, max = 30)
    private String status;
}
