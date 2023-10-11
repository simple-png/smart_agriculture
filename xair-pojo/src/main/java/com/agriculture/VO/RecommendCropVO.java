package com.agriculture.VO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecommendCropVO implements Serializable {
    private Long id;//作物id
    private String name;//作物名字
    private String growthCycle;//生产周期
    private String yield;//产量
    private String characteristics;//特点
    private String cultivationTechniques;//栽培技术要点
    private String promotion;//推广情况
    private String categoryName;//类别名字
    private String fieldId;//田地id
    private String fieldName;//田地名字
}
