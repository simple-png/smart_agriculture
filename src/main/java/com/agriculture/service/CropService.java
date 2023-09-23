package com.agriculture.service;

import com.agriculture.common.result.PageResult;
import com.agriculture.pojo.DTO.CropDTO;
import com.agriculture.pojo.DTO.CropPageQueryDTO;
import com.agriculture.pojo.VO.CropVO;

import java.util.List;

public interface CropService {
    /**
     * 农作物分页查询
     * @param cropPageQueryDTO
     * @return
     */
    PageResult pageQuery(CropPageQueryDTO cropPageQueryDTO);

    /**
     * 农作物添加
     * @param cropDTO
     */
    void add(CropDTO cropDTO);


    /**
     * 根据id查询农作物
     * @param id
     * @return
     */
    CropVO getById(Long id);

    /**
     * 更新农作物
     * @param cropDTO
     */
    void update(CropDTO cropDTO);

    /**
     * 批量删除农作物
     * @param ids
     */
    void deleteBatch(List<Long> ids);

}
