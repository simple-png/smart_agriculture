package com.agriculture.service;

import com.agriculture.DTO.AdminDTO;
import com.agriculture.entity.Admin;

public interface AdminService {
    public Admin login(AdminDTO adminDTO);
}
