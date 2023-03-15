package ru.safin.donation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "payout_settings")
public class PayoutSettings  extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotNull
    @JsonIgnore
    private User user;

    @Column(precision = 10, scale = 2)
    @NotNull
    private BigDecimal balance;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "payoutSettings")
    private List<PayoutMethod> payoutMethod;
}
