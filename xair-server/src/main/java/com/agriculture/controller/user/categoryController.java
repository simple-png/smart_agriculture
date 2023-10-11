package com.agriculture.controller.user;

import com.agriculture.result.PageResult;
import com.agriculture.result.Result;
import com.agriculture.DTO.CropCategoryDTO;
import com.agriculture.DTO.CropCategoryPageQueryDTO;
import com.agriculture.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("userCategoryController")
@Slf4j
@RequestMapping("/user/category")
@Api(tags = "农作物类别api")
public class categoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation("农作物种类分页查询")
    @GetMapping("/list")
    public Result<PageResult> page(CropCategoryPageQueryDTO cropCategoryPageQueryDTO) {
        log.info("农作物种类分页查询");
        PageResult pageResult = categoryService.pageQuery(cropCategoryPageQueryDTO);
        return Result.success(pageResult);
    }

    @ApiOperation("根据id查询农作物种类")
    @GetMapping("/{id}")
    public Result<CropCategoryDTO> getById(@PathVariable Long id) {
        log.info("根据id:{}查询种类", id);
        CropCategoryDTO CropCategoryDTO = categoryService.getById(id);
        return Result.success(CropCategoryDTO);
    }


}
