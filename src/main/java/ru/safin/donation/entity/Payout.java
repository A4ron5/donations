package ru.safin.donation.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import ru.safin.donation.dto.enums.PayoutStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "payouts")
@EntityListeners(AuditingEntityListener.class)
public class Payout  extends AbstractEntity {
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
    @CreatedDate
    private LocalDateTime payoutDate;

    @Column(precision = 10, scale = 2)
    @NotNull
    private BigDecimal fee;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PayoutStatus status;
}
