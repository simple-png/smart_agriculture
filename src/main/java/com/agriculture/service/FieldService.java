package com.agriculture.service;

import com.agriculture.pojo.DTO.FieldDTO;
import com.agriculture.pojo.entity.Field;
import com.agriculture.pojo.entity.GrowthCycle;

import java.util.List;

public interface FieldService {
    /**
     * 添加田地
     *
     * @param fieldDTO
     */
    void addField(FieldDTO fieldDTO);

    /**
     * 更新湿度
     *
     * @param moisture
     */
    void updateMoisture(Long id, String moisture);

    /**
     * 根据id删除田地
     *
     * @param id
     */
    void deleteFieldById(Long id);

    /**
     * 更新田地
     *
     * @param fieldDTO
     */
    void updateField(FieldDTO fieldDTO);

    /**
     * 根据id获取田地信息
     *
     * @param id
     * @return
     */
    Field getById(Long id);

    /**
     * 根据用户id获取田地信息
     *
     * @param userId
     * @return
     */
    List<Field> getByUserId(Long userId);

    /**
     * 判断是否需要浇水
     *
     * @param id
     * @param growthCycle
     */
    String isWatering(Long id, GrowthCycle growthCycle);

    /**
     * 根据农作物id来恢复田地为未耕种模式
     * @param cropId
     */
    void updateToUncultivatedByCropId(Long cropId);
}
