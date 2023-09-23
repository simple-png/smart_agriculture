package com.agriculture.controller.user;

import com.agriculture.common.result.PageResult;
import com.agriculture.common.result.Result;
import com.agriculture.pojo.DTO.CropPageQueryDTO;
import com.agriculture.pojo.VO.CropVO;
import com.agriculture.service.CropService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("userCropController")
@Slf4j
@RequestMapping("/user/crop")
@Api(tags = "农作物api")
public class cropController {
    @Autowired
    private CropService cropService;

    @Cacheable(cacheNames = "cropPageQuery", key = "#cropPageQueryDTO.page+'&'+#cropPageQueryDTO.pageSize")
    @GetMapping("/list")
    @ApiOperation("分页查询")
    public Result<PageResult> page(CropPageQueryDTO cropPageQueryDTO) {
        log.info("农作物分页查询");
        PageResult pageResult = cropService.pageQuery(cropPageQueryDTO);
        return Result.success(pageResult);
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id查询农作物")
    public Result<CropVO> getById(@PathVariable Long id) {
        log.info("查询id为:{}的农作物", id);
        CropVO cropVO = cropService.getById(id);
        return Result.success(cropVO);
    }
}
