package com.agriculture.pojo.VO;

import com.agriculture.pojo.entity.Province;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CropVO implements Serializable {
    private Long id;
    private String name;
    private String growthCycle;//生产周期
    private String yield;//产量
    private String characteristics;//特点
    private String cultivationTechniques;//栽培技术要点
    private String promotion;//推广情况
    private Long cropCategoryId;//类别id
    private String cropCategoryName;//类别名字
    private List<Province> suitableProvince;//适合栽种的省份
}
