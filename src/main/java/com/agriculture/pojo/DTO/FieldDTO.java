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
public class FieldDTO implements Serializable {
    private Long id;
    private Long userId; //用户id
    private String name; //田地名字
    private Long cropId; //种植农作物的id
    private Integer provinceId;//省份id
}