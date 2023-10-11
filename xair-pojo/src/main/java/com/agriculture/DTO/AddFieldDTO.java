package com.agriculture.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddFieldDTO {
    private String name; //田地名字
    private Long cropId; //种植农作物的id
    private Integer provinceId;//省份id
}
