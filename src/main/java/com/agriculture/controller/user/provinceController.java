package com.agriculture.controller.user;

import com.agriculture.common.result.Result;
import com.agriculture.pojo.entity.Province;
import com.agriculture.service.ProvinceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("userProvinceController")
@RequestMapping("/user/province")
@Slf4j
public class provinceController {
    @Autowired
    private ProvinceService provinceService;

    @Cacheable(cacheNames = "province",key = "'list'")
    @GetMapping("list")
    public Result<List<Province>> list() {
        log.info("列出所有省份");
        List<Province> list = provinceService.list();
        return Result.success(list);
    }
}
