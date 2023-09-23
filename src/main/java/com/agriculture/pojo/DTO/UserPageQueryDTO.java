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
public class UserPageQueryDTO implements Serializable {
    private int page;

    private int pageSize;

    private String name;

    private String username;

    private String phone;
}
