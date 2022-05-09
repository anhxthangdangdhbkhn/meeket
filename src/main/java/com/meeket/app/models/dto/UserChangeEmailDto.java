package com.meeket.app.models.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserChangeEmailDto {
    private String email;
    private String password;
}
