package com.agriculture.mapper;

import com.agriculture.entity.News;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NewsMapper {
    @Insert("insert into news(title, url, create_time) VALUES (#{title},#{url},#{createTime})")
    void insert(News news);

    @Select("select * from news where DATE(create_time)=#{dateTime}")
    List<News> getByDate(String dateTime);
}
