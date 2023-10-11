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
public class Crop implements Serializable {
    private Long id;
    private String name;
    private String growthCycle;//生产周期
    private String yield;//产量
    private String characteristics;//特点
    private String cultivationTechniques;//栽培技术要点
    private String promotion;//推广情况
    private Long cropCategoryId;//类别id
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Long createUser;
    private Long updateUser;
}
