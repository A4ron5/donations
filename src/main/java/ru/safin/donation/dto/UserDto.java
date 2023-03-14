package ru.safin.donation.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class UserDto {
    private String nickname;
    private String email;
    private String link_picture;
}
