package com.meeket.app.models.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserChangePassDto {
    private String newPassword;
    private String oldPassword;
}
