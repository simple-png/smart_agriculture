package com.agriculture.service;

import com.agriculture.entity.Echarts;

import java.util.List;

public interface LeaderBoardService {
    /**
     *
     * @param provinceId
     * @return
     */
    List<Echarts> getRankByProvinceId(Integer provinceId);
}
