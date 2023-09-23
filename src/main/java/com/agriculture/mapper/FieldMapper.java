package com.agriculture.mapper;

import com.agriculture.annotation.AutoFill;
import com.agriculture.common.enumeration.OperationType;
import com.agriculture.pojo.entity.Field;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FieldMapper {

    /**
     * 根据田地id查询
     *
     * @param id
     * @return
     */
    @Select("select * from field where id=#{id}")
    Field getById(Long id);

    /**
     * 添加田地
     *
     * @param field
     */
    @AutoFill(OperationType.INSERT)
    @Insert("insert into field(id, user_id, name, status, soil_moisture, planting_time, crop_id, province_id, create_time, update_time, create_user, update_user) VALUES (#{id},#{userId},#{name},#{status},#{soilMoisture},#{plantingTime},#{cropId},#{provinceId},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    void addField(Field field);

    /**
     * 更新田地
     *
     * @param field
     */
    @AutoFill(OperationType.UPDATE)
    void updateWithField(Field field);

    /**
     * 根据id删除田地
     *
     * @param id
     */
    @Delete("delete from field where id=#{id}")
    void deleteFieldById(Long id);

    /**
     * 根据用户id获取田地
     *
     * @param userId
     * @return
     */
    @Select("select * from field where user_id=#{userId}")
    List<Field> listByUserId(Long userId);

    /**
     * 根据农作物id获取田地
     * @param cropId
     * @return
     */
    @Select("select * from field where crop_id=#{cropId}")
    List<Field> listByCropId(Long cropId);

}
