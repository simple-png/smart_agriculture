package com.agriculture.service.impl;

import com.agriculture.constant.MessageConstant;
import com.agriculture.context.BaseContext;
import com.agriculture.exception.AccountNotFoundException;
import com.agriculture.exception.LackInformationException;
import com.agriculture.exception.PasswordErrorException;
import com.agriculture.exception.UserNotFoundException;
import com.agriculture.result.PageResult;
import com.agriculture.mapper.UserMapper;
import com.agriculture.DTO.UserDTO;
import com.agriculture.DTO.UserPageQueryDTO;
import com.agriculture.DTO.UserUpdateDTO;
import com.agriculture.DTO.UserUpdateWithUserDTO;
import com.agriculture.entity.User;
import com.agriculture.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class userServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = DigestUtils.md5DigestAsHex(userDTO.getPassword().getBytes());
        User user = userMapper.login(username);
        if (user == null)
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        if (!user.getPassword().equals(password))
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        return user;
    }

    @Override
    public PageResult pageQuery(UserPageQueryDTO dto) {
        PageHelper.startPage(dto.getPage(), dto.getPageSize());
        Page<User> page = userMapper.pageQuery(dto);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void update(UserUpdateDTO dto) {
        User user = userMapper.getById(dto.getId());
        if (user == null)
            throw new UserNotFoundException(MessageConstant.USER_NOT_FOUND);
        // 设为md5加密
        user.setPassword(DigestUtils.md5DigestAsHex(dto.getPassword().getBytes()));
        userMapper.update(user);
    }

    @Override
    public void register(UserUpdateDTO dto) {
        if (dto.getPassword() == null || dto.getUsername() == null)
            throw new LackInformationException(MessageConstant.LACK_INFORMATION);
        dto.setPassword(DigestUtils.md5DigestAsHex(dto.getPassword().getBytes()));
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        userMapper.insert(user);
    }



    @Override
    public void deleteWithUser(String password) {
        User user = userMapper.getById(BaseContext.getCurrentId());
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(user.getPassword()))
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        userMapper.deleteById(BaseContext.getCurrentId());
    }

    @Override
    public void updateWithUser(UserUpdateWithUserDTO dto) {
        String md5OldPassword = DigestUtils.md5DigestAsHex(dto.getOldPassword().getBytes());
        User user = userMapper.getById(BaseContext.getCurrentId());
        if (!user.getPassword().equals(md5OldPassword))
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        String md5NewPassword = DigestUtils.md5DigestAsHex(dto.getNewPassword().getBytes());
        user.setPassword(md5NewPassword);
        userMapper.update(user);
    }
}
