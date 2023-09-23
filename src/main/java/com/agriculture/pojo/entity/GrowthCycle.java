package com.agriculture.pojo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class GrowthCycle implements Serializable {
    private Long id;
    private Long categoryId;//种类id
    private String name;//周期名字
    private String cyclePercent;//在整个周期的占比:如1-2
    private String soilMoisture;//这个周期需要的土壤湿度:如22.33
}
