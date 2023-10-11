package com.agriculture.controller.user;

import com.agriculture.result.PageResult;
import com.agriculture.result.Result;
import com.agriculture.DTO.CropPageQueryDTO;
import com.agriculture.VO.CropOptionVO;
import com.agriculture.VO.CropVO;
import com.agriculture.service.CropService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("userCropController")
@Slf4j
@RequestMapping("/user/crop")
@Api(tags = "农作物api")
public class cropController {
    @Autowired
    private CropService cropService;
    @Autowired
    private RedisTemplate redisTemplate;
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
    @GetMapping("/category/{id}")
    @ApiOperation("根据类别id获取所有农作物")
    public Result<List<CropVO>> getByCategoryId(@PathVariable Long id) {
        log.info("查询id为:{}的农作物", id);
        List<CropVO> cropVO = cropService.getByCategoryId(id);
        return Result.success(cropVO);
    }

    @GetMapping("/all")
    @ApiOperation("获取所有农作物的id和name")
    public Result<List<CropOptionVO>> listAll(){
        log.info("获取所有农作物的id和name");
        List<CropOptionVO> list=cropService.listAll();
        return Result.success(list);
    }
}
