package com.agriculture.controller.admin;

import com.agriculture.result.Result;
import com.agriculture.DTO.FieldDTO;
import com.agriculture.VO.FieldVO;
import com.agriculture.service.FieldService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Result<FieldVO> getById(@PathVariable Long id) {
        log.info("根据田地id:{}获取数据", id);
        FieldVO field = fieldService.getById(id);
        return Result.success(field);
    }
}
