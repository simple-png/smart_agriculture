package com.agriculture.mapper;

import com.agriculture.entity.LoginLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogMapper {
    @Insert("insert into login_log(username, ip, ip_source, os, browser, description, create_time, user_agent) VALUES " +
            "(#{username},#{ip},#{ipSource},#{os},#{browser},#{description},#{createTime},#{userAgent})")
    void addLoginLog(LoginLog loginLog);
}
