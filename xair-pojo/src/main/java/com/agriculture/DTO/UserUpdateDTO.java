package com.agriculture.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdateDTO implements Serializable {
    private Long id;
    private String name;
    private String username;
    private String phone;
    private String password;
}
