package ru.safin.donation.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nickname;

    @NotNull
    @Email
    private String email;

    private String link_picture;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Donate> donates;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Payout> payouts;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "user")
    private UserSettings userSettings;
}