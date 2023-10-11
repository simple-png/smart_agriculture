package com.agriculture.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CropPageQueryDTO implements Serializable {
    private int page;

    private int pageSize;

    private String name;

    private Integer categoryId; //分类id

    private String growthCycle;//生长周期

    private String yield;//产量

    private String characteristics;//特点

    private String provinceName;//省份名字

}
