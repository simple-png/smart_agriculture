package com.agriculture.controller.user;

import com.agriculture.common.context.IpContext;
import com.agriculture.common.result.Result;
import com.agriculture.pojo.entity.Weather;
import com.agriculture.service.AmapService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
@Slf4j
@Api(tags = "ip地址api")
public class WeatherController {
    //高德地图相关
    @Autowired
    private AmapService amapService;

    @ApiOperation("获取当前天气")
    @GetMapping("/weather")
    public Result<Weather> getWeather() {
        log.info("获取当前天气");
        Weather weather = amapService.getWeather();
        return Result.success(weather);
    }

    @ApiOperation("获取用户ip地址")
    @GetMapping("/ip")
    public Result getIp(HttpServletRequest request) {
        String ipAddress = request.getRemoteAddr();
        IpContext.setIpAddress(ipAddress);
        return Result.success();
    }
}
