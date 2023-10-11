package com.agriculture.mapper;

import com.agriculture.annotation.AutoFill;
import com.agriculture.enumeration.OperationType;
import com.agriculture.DTO.GrowthCyclePageQueryDTO;
import com.agriculture.entity.GrowthCycle;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GrowthCycleMapper {

    /**
     * 根据种类获取id
     * @param id
     * @return
     */
    @Select("select gc.* from growth_cycle gc where category_id=#{id}")
    List<GrowthCycle> getByCategoryId(Long id);

    /**
     * 根据种类id删除生长周期
     * @param categoryId
     */
    @Delete("delete from growth_cycle where category_id=#{categoryId}")
    void deleteByCategoryId(Long categoryId);

    /**
     * 添加生长周期
     * @param growthCycle
     */
    @AutoFill(OperationType.INSERT)
    @Insert("insert into growth_cycle(category_id, name, cycle_percent, soil_moisture) VALUES (#{categoryId},#{name},#{cyclePercent},#{soilMoisture})")
    void add(GrowthCycle growthCycle);

    /**
     * 生长周期条件分页查询
     * @param dto
     * @return
     */
    Page<GrowthCycle> pageQuery(GrowthCyclePageQueryDTO dto);

    /**
     * 根据id删除生长周期
     * @param id
     */
    @Delete("delete from growth_cycle where id=#{id}")
    void deleteById(Long id);

    /**
     * 根据id获取生长周期
     * @param id
     */
    @Select("select * from growth_cycle where id=#{id}")
    GrowthCycle getById(Long id);

    /**
     * 更新生长周期
     * @param growthCycle
     */
    @AutoFill(OperationType.UPDATE)
    void update(GrowthCycle growthCycle);
}
