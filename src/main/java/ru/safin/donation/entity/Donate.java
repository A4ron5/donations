package ru.safin.donation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import ru.safin.donation.dto.enums.DonateStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "donates")
@EntityListeners(AuditingEntityListener.class)
public class Donate extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotNull
    @JsonIgnore
    private User user;

    @Column(precision = 10, scale = 2)
    @NotNull
    private BigDecimal sum;

    @Column(name = "donate_date", columnDefinition = "TIMESTAMP")
    @NotNull
    @CreatedDate
    private LocalDateTime donateDate;

    @Column(columnDefinition = "TEXT")
    @NotNull
    private String text;

    @NotNull
    private String fromUser;

    @NotNull
    private Boolean fee_coverage;

    @NotNull
    @Enumerated(EnumType.STRING)
    private DonateStatus status;

    @NotNull
    private String hash;
}
