package com.agriculture.service;

import com.agriculture.result.PageResult;
import com.agriculture.DTO.UserDTO;
import com.agriculture.DTO.UserPageQueryDTO;
import com.agriculture.DTO.UserUpdateDTO;
import com.agriculture.DTO.UserUpdateWithUserDTO;
import com.agriculture.entity.User;

public interface UserService {
    /**
     * 用户登录
     * @param userDTO
     * @return
     */
    User login(UserDTO userDTO);

    /**
     * 用户信息条件查询
     * @param dto
     * @return
     */
    PageResult pageQuery(UserPageQueryDTO dto);

    /**
     * 管理端用户信息修改
     * @param dto
     */
    void update(UserUpdateDTO dto);

    /**
     * 用户注册
     * @param dto
     */
    void register(UserUpdateDTO dto);

    /**
     * 根据密码来注销账户
     * @param password
     */
    void deleteWithUser(String password);

    /**
     * 用户端用户信息修改
     * @param dto
     */
    void updateWithUser(UserUpdateWithUserDTO dto);
}
