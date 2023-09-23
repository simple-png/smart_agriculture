package com.agriculture.controller.user;

import com.agriculture.common.result.Result;
import com.agriculture.pojo.entity.GrowthCycle;
import com.agriculture.service.CropCycleService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("userGrowthCycleController")
@RequestMapping("/user/growth")
@Api(tags = "根据田地id获取该田地作物的生长周期")
public class growthCycleController {
    @Autowired
    private CropCycleService cropCycleService;

    @GetMapping("/{id}")
    public Result<GrowthCycle> getGrowthCycle(@PathVariable Long id) {
        log.info("获取{}号田地农作物的生产周期", id);
        GrowthCycle growth = cropCycleService.getGrowthCycleByFieldId(id);
        return Result.success(growth);
    }
}
