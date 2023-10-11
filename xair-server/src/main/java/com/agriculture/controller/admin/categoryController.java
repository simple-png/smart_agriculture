package com.agriculture.controller.admin;

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

@RestController("adminCategoryController")
@Slf4j
@RequestMapping("/admin/category")
@Api(tags = "农作物类别api")
public class categoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation("添加农作物种类")
    @PostMapping("/add")
    public Result add(@RequestBody CropCategoryDTO cropCategoryDTO) {
        log.info("添加种类:{}", cropCategoryDTO);
        categoryService.add(cropCategoryDTO);
        return Result.success();
    }

    @ApiOperation("更新农作物种类")
    @PostMapping("/update")
    public Result update(@RequestBody CropCategoryDTO cropCategoryDTO) {
        log.info("更新种类:{}", cropCategoryDTO);
        categoryService.update(cropCategoryDTO);
        return Result.success();
    }

    @ApiOperation("删除农作物种类")
    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable Long id) {
        log.info("删除种类:{}", id);
        categoryService.delete(id);
        return Result.success();
    }

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
