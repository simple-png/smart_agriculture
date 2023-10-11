package com.agriculture.service;

import com.agriculture.result.PageResult;
import com.agriculture.DTO.CropCategoryDTO;
import com.agriculture.DTO.CropCategoryPageQueryDTO;

public interface CategoryService {
    /**
     * 添加种类
     * @param cropCategoryDTO
     */
    void add(CropCategoryDTO cropCategoryDTO);

    /**
     * 修改种类
     * @param cropCategoryDTO
     */
    void update(CropCategoryDTO cropCategoryDTO);

    /**
     * 删除种类
     * @param id
     */
    void delete(Long id);

    /**
     * 条件分页查询
     * @param cropCategoryPageQueryDTO
     * @return
     */
    PageResult pageQuery(CropCategoryPageQueryDTO cropCategoryPageQueryDTO);

    /**
     * 根据id查询种类
     * @param id
     * @return
     */
    CropCategoryDTO getById(Long id);
}