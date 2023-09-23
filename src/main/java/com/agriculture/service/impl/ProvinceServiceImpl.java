package com.agriculture.service.impl;

import com.agriculture.mapper.ProvinceMapper;
import com.agriculture.pojo.entity.Province;
import com.agriculture.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceServiceImpl implements ProvinceService {
    @Autowired
    private ProvinceMapper provinceMapper;
    @Override
    public List<Province> list() {
        return provinceMapper.all();
    }
}
