package com.agriculture.controller.user;

import com.agriculture.DTO.UserDTO;
import com.agriculture.DTO.UserUpdateDTO;
import com.agriculture.DTO.UserUpdateWithUserDTO;
import com.agriculture.VO.UserVO;
import com.agriculture.annotation.LoginLogAnnotation;
import com.agriculture.constant.JwtClaimsConstant;
import com.agriculture.context.BaseContext;
import com.agriculture.entity.User;
import com.agriculture.mapper.UserMapper;
import com.agriculture.properties.JwtProperties;
import com.agriculture.result.Result;
import com.agriculture.service.UserService;
import com.agriculture.utils.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/user")
@Api(tags = "用户登录")
public class userLoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private UserMapper userMapper;


    /**
     * 登录
     *
     * @param userDTO
     * @return
     */
    @ApiOperation("用户登录")
    @PostMapping("/login")
    @LoginLogAnnotation
    public Result<UserVO> login(@RequestBody UserDTO userDTO) {
        log.info("用户登录：{}", userDTO);
        User user = userService.login(userDTO);
        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getUserSecretKey(),
                jwtProperties.getUserTtl(),
                claims);
        UserVO userVO1 = UserVO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .token(token)
                .build();
        return Result.success(userVO1);
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public Result register(@RequestBody UserUpdateDTO dto) {
        log.info("用户注册:{}", dto);
        userService.register(dto);
        return Result.success();
    }

    @ApiOperation("用户信息修改")
    @PostMapping("/update")
    public Result update(@RequestBody UserUpdateWithUserDTO dto) {
        log.info("用户信息修改:{}", dto);
        userService.updateWithUser(dto);
        return Result.success();
    }

    @ApiOperation("用户注销账号")
    @PostMapping("/logout")
    public Result logout(String password) {
        log.info("注销用户:{}", BaseContext.getCurrentId());
        User user = userMapper.getById(BaseContext.getCurrentId());
        userService.deleteWithUser(password);
        return Result.success();
    }
}
