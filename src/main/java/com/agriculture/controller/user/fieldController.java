package com.agriculture.controller.user;

import com.agriculture.common.context.BaseContext;
import com.agriculture.common.result.Result;
import com.agriculture.pojo.DTO.AddFieldDTO;
import com.agriculture.pojo.DTO.FieldDTO;
import com.agriculture.pojo.DTO.RecommendQueryDTO;
import com.agriculture.pojo.VO.RecommendCropVO;
import com.agriculture.pojo.entity.Field;
import com.agriculture.service.CropService;
import com.agriculture.service.FieldService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("userFieldController")
@RequestMapping("/user/field")
@Slf4j
@Api(tags = "田地api")
public class fieldController {
    @Autowired
    private FieldService fieldService;
    @Autowired
    private CropService cropService;

    @PostMapping("/add")
    @ApiOperation("添加用户田地")
    public Result addField(@RequestBody AddFieldDTO addFieldDTO) {
        log.info("添加用户田地:{}", addFieldDTO);
        fieldService.userAddField(addFieldDTO);
        return Result.success();
    }

    @PostMapping("/delete/{id}")
    @ApiOperation("根据田地id删除用户田地")
    public Result deleteField(@PathVariable("id") Long fieldId) {
        log.info("删除用户田地{}", fieldId);
        fieldService.deleteFieldById(fieldId);
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
    @ApiOperation("根据田地id获取田地")
    public Result<Field> getById(@PathVariable Long id) {
        log.info("根据田地id:{}获取数据", id);
        Field field = fieldService.getById(id);
        return Result.success(field);
    }

    @GetMapping("/all")
    @ApiOperation("获取用户id的所有田地")
    public Result<List<Field>> getByUserId() {
        log.info("根据用户id获取所有田地信息");
        List<Field> list = fieldService.getByUserId(BaseContext.getCurrentId());
        return Result.success(list);
    }

    @PostMapping("/soil/{id}")
    @ApiOperation("根据田地id发送当前湿度")
    public Result getSoilMoisture(@PathVariable("id") Long id, String moisture) {
        log.info("监控{}田地的湿度:{}", id, moisture);
        fieldService.updateMoisture(id, moisture);
        return Result.success();
    }

    @PostMapping("/iswatering/{id}")
    @ApiOperation("是否要浇水,id为田地id")
    public Result<String> isWatering(@PathVariable Long id) {
        log.info("是否需要浇水");
        String watering = fieldService.isWatering(id);
        return Result.success(watering);
    }

    @ApiOperation("推荐用户种地(有选择性是否根据种类推荐)")
    @GetMapping("/recommend")
    public Result<List<RecommendCropVO>> recommendCrop(RecommendQueryDTO dto) {
        log.info("推荐用户种地");
        List<RecommendCropVO> list = cropService.recommendCropByUserId(dto);
        return Result.success(list);
    }
}
