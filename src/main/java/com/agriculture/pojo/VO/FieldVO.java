package com.agriculture.pojo.VO;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class FieldVO implements Serializable {
    private Long id;
    private String name; //田地名字
    private Integer status; //田地状态: 1.已耕种 2.未耕种
    private String soilMoisture; //土壤湿度
    private LocalDateTime plantingTime; //种植时间
    private String cropName; //种植农作物的id
    private String provinceName;//省份id
}
