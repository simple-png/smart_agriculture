package com.agriculture.controller.admin;

import com.agriculture.result.PageResult;
import com.agriculture.result.Result;
import com.agriculture.DTO.UserPageQueryDTO;
import com.agriculture.DTO.UserUpdateWithUserDTO;
import com.agriculture.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/user")
@Slf4j
@Api(tags = "用户api")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation("用户分页查询")
    @GetMapping("/list")
    public Result<PageResult> page(UserPageQueryDTO dto) {
        log.info("用户信息分页查询");
        PageResult pageResult = userService.pageQuery(dto);
        return Result.success(pageResult);
    }

    @ApiOperation("用户信息修改")
    @PostMapping("/update")
    public Result update(@RequestBody UserUpdateWithUserDTO dto) {
        log.info("用户信息修改:{}", dto);
        userService.updateWithUser(dto);
        redisTemplate.delete("user" + "::" + dto.getUsername().hashCode());
        return Result.success();
    }
}
