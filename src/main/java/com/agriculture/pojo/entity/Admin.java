package com.agriculture.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Admin implements Serializable {
    private Long id;
    private String name;
    private String username;
    private String password;
}
