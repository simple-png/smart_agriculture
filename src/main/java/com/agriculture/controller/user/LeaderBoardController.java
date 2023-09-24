package com.agriculture.controller.user;

import com.agriculture.common.result.Result;
import com.agriculture.pojo.entity.Echarts;
import com.agriculture.service.LeaderBoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/user/rank")
@Api(tags = "Echarts饼状图")
public class LeaderBoardController {
    @Autowired
    private LeaderBoardService leaderBoardService;

    @GetMapping
    @ApiOperation("根据省份获取各个农作物总量")
    public Result<List<Echarts>> getRankByProvinceId(Integer provinceId) {
        List<Echarts> list = leaderBoardService.getRankByProvinceId(provinceId);
        return Result.success(list);
    }
}
