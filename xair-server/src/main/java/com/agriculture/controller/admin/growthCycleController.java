package com.agriculture.controller.admin;

import com.agriculture.result.PageResult;
import com.agriculture.result.Result;
import com.agriculture.DTO.GrowthCyclePageQueryDTO;
import com.agriculture.entity.GrowthCycle;
import com.agriculture.service.CropCycleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController("adminGrowthCycleController")
@RequestMapping("/admin/growth")
@Api(tags = "根据田地id获取该田地作物的生长周期")
public class growthCycleController {
    @Autowired
    private CropCycleService cropCycleService;

    @ApiOperation("根据田地id获取农作物的生产周期")
    @GetMapping("/{id}")
    public Result<GrowthCycle> getGrowthCycle(@PathVariable Long id) {
        log.info("获取{}号田地农作物的生产周期", id);
        GrowthCycle growth = cropCycleService.getGrowthCycleByFieldId(id);
        return Result.success(growth);
    }

    @ApiOperation("添加生长周期")
    @PostMapping("/add")
    public Result add(@RequestBody GrowthCycle growthCycle){
        log.info("添加生长周期");
        cropCycleService.add(growthCycle);
        return Result.success();
    }

    @ApiOperation("生长周期分页查询")
    @GetMapping("/list")
    public Result<PageResult> page(GrowthCyclePageQueryDTO dto){
        log.info("生长周期分页查询:{}",dto);
        PageResult pageResult = cropCycleService.pageQuery(dto);
        return Result.success(pageResult);
    }

    @ApiOperation("生长周期批量删除")
    @PostMapping("/delete")
    public Result deleteByIds(@RequestBody List<Long> ids){
        log.info("生长周期批量删除:{}",ids);
        cropCycleService.deleteByIds(ids);
        return Result.success();
    }

    @ApiOperation("修改生长周期")
    @PostMapping("/update")
    public Result update(GrowthCycle growthCycle){
        log.info("修改生长周期:{}",growthCycle);
        cropCycleService.update(growthCycle);
        return Result.success();
    }

}
