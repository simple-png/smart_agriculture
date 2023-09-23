package com.agriculture.controller.admin;

import com.agriculture.common.context.BaseContext;
import com.agriculture.common.result.Result;
import com.agriculture.pojo.DTO.FieldDTO;
import com.agriculture.pojo.entity.Field;
import com.agriculture.pojo.entity.GrowthCycle;
import com.agriculture.service.FieldService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("adminFieldController")
@RequestMapping("/admin/field")
@Slf4j
@Api(tags = "田地api")
public class fieldController {
    @Autowired
    private FieldService fieldService;

    @PostMapping("/add")
    @ApiOperation("添加用户田地")
    public Result addField(@RequestBody FieldDTO fieldDTO) {
        log.info("添加用户田地:{}", fieldDTO);
        fieldService.addField(fieldDTO);
        return Result.success();
    }

    @PostMapping("/delete/{id}")
    @ApiOperation("删除用户田地")
    public Result deleteField(@PathVariable Long id) {
        log.info("删除用户田地{}", id);
        fieldService.deleteFieldById(id);
        return Result.success();
    }

    @PostMapping("/update")
    @ApiOperation("更新用户田地")
    public Result updateField(@RequestBody FieldDTO fieldDTO) {
        log.info("更新田地：{}", fieldDTO);
        fieldService.updateField(fieldDTO);
        return Result.success();
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id获取田地")
    public Result<Field> getById(@PathVariable Long id) {
        log.info("根据田地id:{}获取数据", id);
        Field field = fieldService.getById(id);
        return Result.success(field);
    }

    @GetMapping("/all")
    @ApiOperation("获取当前登录用户id的所有田地")
    public Result<List<Field>> getByUserId() {
        log.info("根据当前登录用户id获取所有田地信息");
        List<Field> list = fieldService.getByUserId(BaseContext.getCurrentId());
        return Result.success(list);
    }

    @PostMapping("/soil/{id}")
    @ApiOperation("根据田地id监控湿度(发送湿度)")
    public Result getSoilMoisture(@PathVariable("id") Long id, @RequestParam String moisture) {
        log.info("监控{}田地的湿度:{}", id, moisture);
        fieldService.updateMoisture(id, moisture);
        return Result.success();
    }

    @PostMapping("/iswatering/{id}")
    @ApiOperation("是否要浇水")
    public Result<String> isWatering(@RequestBody GrowthCycle growthCycle,@PathVariable Long id) {
        log.info("是否需要浇水");
        String watering = fieldService.isWatering(id, growthCycle);
        return Result.success(watering);
    }
}
