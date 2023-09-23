package com.agriculture.mapper;

import com.agriculture.annotation.AutoFill;
import com.agriculture.common.enumeration.OperationType;
import com.agriculture.pojo.DTO.CropCategoryPageQueryDTO;
import com.agriculture.pojo.VO.CropCategoryVO;
import com.agriculture.pojo.entity.CropCategory;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CropCategoryMapper {

    /**
     * 根据id查询种类
     *
     * @param id
     * @return
     */
    @Select("select * from crop_category where id=#{id}")
    CropCategory getById(Long id);

    /**
     * 添加种类
     *
     * @param category
     */
    @Insert("insert into crop_category(name, create_time, update_time, create_user, update_user) values (#{name},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    @AutoFill(OperationType.INSERT)
    void add(CropCategory category);

    /**
     * 更新种类
     *
     * @param category
     */
    @Update("update crop_category set name=#{name},update_time=#{updateTime},update_user=#{updateUser} where id=#{id}")
    @AutoFill(OperationType.UPDATE)
    void update(CropCategory category);

    /**
     * 条件分页拆查询
     * @param dto
     * @return
     */
    Page<CropCategoryVO> pageQuery(CropCategoryPageQueryDTO dto);

    /**
     * 根据id删除种类
     * @param categoryId
     */
    @Delete("delete from crop_category where id=#{categoryId}")
    void deleteById(Long categoryId);
}
