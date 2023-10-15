package com.agriculture.service.impl;

import com.agriculture.constant.MessageConstant;
import com.agriculture.exception.AccountNotFoundException;
import com.agriculture.exception.PasswordErrorException;
import com.agriculture.mapper.AdminMapper;
import com.agriculture.DTO.AdminDTO;
import com.agriculture.entity.Admin;
import com.agriculture.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class adminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin login(AdminDTO adminDTO) {
        String username = adminDTO.getUsername();
        String password = DigestUtils.md5DigestAsHex(adminDTO.getPassword().getBytes());
        Admin admin = adminMapper.login(username);
        if (admin==null)
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        if (!password.equals(admin.getPassword()))
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);

        return admin;
    }
}
