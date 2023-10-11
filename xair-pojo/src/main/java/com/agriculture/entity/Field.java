package com.agriculture.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Field implements Serializable {
    private Long id;
    private Long userId; //用户id
    private String name; //田地名字
    private Integer status; //田地状态: 1.已耕种 2.未耕种
    private String soilMoisture; //土壤湿度
    private LocalDateTime plantingTime; //种植时间
    private Long cropId; //种植农作物的id
    private Integer provinceId;//省份id
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Long createUser;
    private Long updateUser;
}
