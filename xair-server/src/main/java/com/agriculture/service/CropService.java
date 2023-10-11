package com.agriculture.service;

import com.agriculture.result.PageResult;
import com.agriculture.DTO.CropDTO;
import com.agriculture.DTO.CropPageQueryDTO;
import com.agriculture.DTO.RecommendQueryDTO;
import com.agriculture.VO.CropOptionVO;
import com.agriculture.VO.CropVO;
import com.agriculture.VO.RecommendCropVO;

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

    /**
     * 推荐用户种植的农作物
     * @param dto
     * @return
     */
    List<RecommendCropVO> recommendCropByUserId(RecommendQueryDTO dto);

    /**
     * 根据类别id查询农作物
     * @param id
     * @return
     */
    List<CropVO> getByCategoryId(Long id);

    /**
     * 获取所有农作物的id和name
     * @return
     */
    List<CropOptionVO> listAll();
}
