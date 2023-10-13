package com.agriculture.service;

import com.agriculture.entity.Weather;

import java.util.Map;

public interface AmapService {

    /**
     * 获取天气预报信息
     *
     * @return
     */
    Weather getWeather();

    /**
     * 获取城市编码
     *
     * @return
     */
    Map<String,Object> getLocation();

}
