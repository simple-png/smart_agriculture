package com.agriculture.mapper;

import com.agriculture.entity.Echarts;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LeaderBoardMapper {
    @Select("select count(f.id) as 'value', c.name from field f join crop c on c.id = f.crop_id where province_id = #{provinceId} group by f.crop_id")
    List<Echarts> getRankByProvinceId(Integer provinceId);
}
