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
public class GrowthCyclePageQueryDTO implements Serializable {
    private int page;

    private int pageSize;

    private String name;

    private Integer categoryId;

    private String cyclePercent;

    private String soilMoisture;

}
