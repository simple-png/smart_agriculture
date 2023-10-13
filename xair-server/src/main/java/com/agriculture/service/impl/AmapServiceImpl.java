package com.agriculture.service.impl;

import com.agriculture.context.IpContext;
import com.agriculture.utils.HttpClientUtil;
import com.agriculture.entity.Weather;
import com.agriculture.service.AmapService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class AmapServiceImpl implements AmapService {
    private final String getIpURL = "https://restapi.amap.com/v3/ip";
    private final String getWeatherURL = "https://restapi.amap.com/v3/weather/weatherInfo";

    @Value("${farm.amap.key}")
    private String key;


    @Override
    public Weather getWeather() {
        Map<String, Object> location = getLocation();
        String adcode = (String) location.get("adcode");
        HashMap<String, String> map = new HashMap<>();
        map.put("city", adcode);
        map.put("key", key);
        String json = HttpClientUtil.doGet(getWeatherURL, map);
        JSONObject jo = JSON.parseObject(json);
        JSONArray livesArray = jo.getJSONArray("lives");
        JSONObject jsonObject = livesArray.getJSONObject(0);
        return Weather.builder()
                .province(jsonObject.getString("province"))
                .city(jsonObject.getString("city"))
                .adcode(jsonObject.getString("adcode"))
                .weather(jsonObject.getString("weather"))
                .temperature(jsonObject.getString("temperature"))
                .winddirection(jsonObject.getString("winddirection"))
                .windpower(jsonObject.getString("windpower"))
                .humidity(jsonObject.getString("humidity"))
                .reporttime(jsonObject.getString("reporttime"))
                .build();
    }

    @Override
    public Map<String,Object> getLocation() {
        HashMap<String, String> map = new HashMap<>();
        String ip = IpContext.getCurrentIp();
        log.info("用户当前ip:{}",ip);
        map.put("key", key);
        map.put("ip",ip);
        String json = HttpClientUtil.doGet(getIpURL, map);
        JSONObject jsonObject = JSON.parseObject(json);
        Map<String, Object> innerMap = jsonObject.getInnerMap();
        return innerMap;
    }
}
