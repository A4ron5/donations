package ru.safin.donation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "user_settings")
public class UserSettings extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @NotNull
    @JsonIgnore
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
}
