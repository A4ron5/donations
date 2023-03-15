package ru.safin.donation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "payout_methods")
@EntityListeners(AuditingEntityListener.class)
public class PayoutMethod extends AbstractEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payout_settings_id")
    @ToString.Exclude
    @JsonIgnore
    private PayoutSettings payoutSettings;

    @NotNull
    private Boolean is_main;

    @NotNull
    private String cardNumber;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    @NotNull
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "modified_at", columnDefinition = "TIMESTAMP")
    @NotNull
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @Column(precision = 10, scale = 2)
    @NotNull
    private BigDecimal fee;
}

