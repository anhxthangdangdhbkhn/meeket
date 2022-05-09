package com.meeket.app.models.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserCreateDto {

    private String username;
    private String password;
    private String email;
}
