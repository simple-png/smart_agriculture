package com.agriculture.controller.admin;

import com.agriculture.result.PageResult;
import com.agriculture.result.Result;
import com.agriculture.DTO.CropDTO;
import com.agriculture.DTO.CropPageQueryDTO;
import com.agriculture.VO.CropVO;
import com.agriculture.service.CropService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("adminCropController")
@Slf4j
@RequestMapping("/admin/crop")
@Api(tags = "农作物api")
public class cropController {
    @Autowired
    private CropService cropService;

    @GetMapping("/list")
    @ApiOperation("分页查询")
    public Result<PageResult> page(CropPageQueryDTO cropPageQueryDTO) {
        log.info("农作物分页查询");
        PageResult pageResult = cropService.pageQuery(cropPageQueryDTO);
        return Result.success(pageResult);
    }

    @PostMapping("/add")
    @ApiOperation("添加农作物")
    public Result add(@RequestBody CropDTO cropDTO) {
        log.info("添加农作物:{}", cropDTO);
        cropService.add(cropDTO);
        return Result.success();
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id查询农作物")
    public Result<CropVO> getById(@PathVariable Long id) {
        log.info("查询id为:{}的农作物", id);
        CropVO cropVO = cropService.getById(id);
        return Result.success(cropVO);
    }

    @PostMapping("/update")
    @ApiOperation("更新农作物")
    public Result update(@RequestBody CropDTO cropDTO) {
        log.info("更新农作物:{}", cropDTO);
        cropService.update(cropDTO);
        return Result.success();
    }

    @PostMapping("/delete")
    @ApiOperation("批量删除农作物")
    public Result delete(@RequestParam List<Long> ids) {
        log.info("农作物批量删除：{}", ids);
        cropService.deleteBatch(ids);
        return Result.success();
    }

}
