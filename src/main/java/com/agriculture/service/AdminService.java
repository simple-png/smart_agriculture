package com.agriculture.service;

import com.agriculture.pojo.DTO.AdminDTO;
import com.agriculture.pojo.entity.Admin;

public interface AdminService {
    public Admin login(AdminDTO adminDTO);
}
