package com.agriculture.mapper;

import com.agriculture.annotation.AutoFill;
import com.agriculture.common.enumeration.OperationType;
import com.agriculture.pojo.DTO.UserPageQueryDTO;
import com.agriculture.pojo.entity.User;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    /**
     * 用户登录
     *
     * @param username
     * @return
     */
    @Select("select * from user where username=#{username}")
    User login(String username);

    /**
     * 用户信息分页查询
     *
     * @param dto
     * @return
     */
    Page<User> pageQuery(UserPageQueryDTO dto);

    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     */
    @Select("select * from user where id = #{id}")
    User getById(Long id);

    /**
     * 更新用户信息
     *
     * @param user
     */
    @AutoFill(OperationType.UPDATE)
    void update(User user);

    /**
     * 用户注册
     *
     * @param user
     */
    @Insert("insert into user(name, username, phone, password, create_time, update_time, create_user, update_user) VALUES (#{name},#{username},#{phone},#{password},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    @AutoFill(OperationType.INSERT)
    void insert(User user);

    /**
     * 根据id删除用户信息
     * @param Id
     */
    @Delete("delete from user where id=#{id}")
    void deleteById(Long Id);
}
