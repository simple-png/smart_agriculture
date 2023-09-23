package com.agriculture.pojo.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdateWithUserDTO implements Serializable {
    private String username;
    private String newPassword;
    private String oldPassword;
}
