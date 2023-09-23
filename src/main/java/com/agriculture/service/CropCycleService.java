package com.agriculture.service;

import com.agriculture.common.result.PageResult;
import com.agriculture.pojo.DTO.GrowthCyclePageQueryDTO;
import com.agriculture.pojo.entity.GrowthCycle;

import java.util.List;

public interface CropCycleService {
    /**
     * 根据田地id获取crop生长周期
     * @param id
     * @return
     */
    GrowthCycle getGrowthCycleByFieldId(Long id);

    /**
     * 添加生长周期
     * @param growthCycle
     */
    void add(GrowthCycle growthCycle);

    /**
     * 生长周期分页查询
     * @param dto
     * @return
     */
    PageResult pageQuery(GrowthCyclePageQueryDTO dto);

    /**
     * 生长周期批量删除
     * @param ids
     */
    void deleteByIds(List<Long> ids);

    /**
     * 修改生长周期
     * @param growthCycle
     */
    void update(GrowthCycle growthCycle);
}
