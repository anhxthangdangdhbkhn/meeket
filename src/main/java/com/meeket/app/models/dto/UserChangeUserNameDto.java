package com.meeket.app.models.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserChangeUserNameDto {
    private String username;
    private String password;
}
