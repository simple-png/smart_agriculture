package com.agriculture.mapper;

import com.agriculture.pojo.entity.Province;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProvinceMapper {
    /**
     * 根据cropId获取省份
     *
     * @param id
     * @return
     */
    @Select("select p.* from province p join crop_province cp on p.id=cp.province_id where cp.crop_id=#{id}")
    List<Province> getProvinceByCrop(Long id);

    /**
     * 获取所有省份
     *
     * @return
     */
    @Select("select * from province")
    List<Province> all();


    /**
     * 根据cropId,provinceId建立联系
     *
     * @param cropId
     * @param provinceId
     */
    @Insert("insert into crop_province(crop_id, province_id) VALUES (#{cropId}, #{provinceId})")
    void addCropWithProvince(Long cropId, Long provinceId);

    /**
     * 根据农产品Id删除关系
     *
     * @param id
     */
    @Delete("delete from crop_province where crop_id=#{id}")
    void deleteByCropId(Long id);

    @Select("select * from province where id=#{id}")
    Province getById(Long id);
}
