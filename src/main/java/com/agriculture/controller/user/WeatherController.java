package com.agriculture.controller.user;

import com.agriculture.common.result.Result;
import com.agriculture.pojo.entity.Weather;
import com.agriculture.service.AmapService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
@Api(tags = "ip地址api")
public class WeatherController {
    //高德地图相关
    @Autowired
    private AmapService amapService;

    @GetMapping("/weather")
    public Result<Weather> getWeather() {
        log.info("获取当前天气");
        Weather weather = amapService.getWeather();
        return Result.success(weather);
    }
}
