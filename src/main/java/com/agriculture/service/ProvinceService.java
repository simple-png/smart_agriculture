package com.agriculture.service;

import com.agriculture.pojo.entity.Province;

import java.util.List;

public interface ProvinceService {

    /**
     * 列出所有省份
     * @return
     */
    List<Province> list();
}
