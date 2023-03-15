package ru.safin.donation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class UserDto {
    @NotNull
    private Long id;
    @NotBlank
    private String nickname;
    @Email
    @NotBlank
    private String email;
    private String link_picture;
}
