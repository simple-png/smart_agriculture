package com.agriculture.service;

import com.agriculture.pojo.entity.Weather;

public interface AmapService {

    /**
     * 获取天气预报信息
     * @return
     */
    Weather getWeather();

    /**
     * 获取城市编码
     * @return
     */
    String getAdcode();
}
