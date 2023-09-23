package com.agriculture.mapper;

import com.agriculture.pojo.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {

    /**
     * 管理员登录
     * @param username
     * @return
     */
    @Select("select * from admin where username=#{username}")
    Admin login(String username);
}
