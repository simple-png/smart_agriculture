package com.agriculture.controller.user;

import com.agriculture.common.constant.JwtClaimsConstant;
import com.agriculture.common.context.BaseContext;
import com.agriculture.common.properties.JwtProperties;
import com.agriculture.common.result.Result;
import com.agriculture.common.utils.JwtUtil;
import com.agriculture.pojo.DTO.UserDTO;
import com.agriculture.pojo.DTO.UserUpdateDTO;
import com.agriculture.pojo.DTO.UserUpdateWithUserDTO;
import com.agriculture.pojo.VO.UserVO;
import com.agriculture.pojo.entity.User;
import com.agriculture.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
    private RedisTemplate redisTemplate;
    private final String cacheName = "user";


    /**
     * 登录
     *
     * @param userDTO
     * @return
     */
    @Cacheable(cacheNames = cacheName, key = "#userDTO.username.hashCode()")
    @ApiOperation("用户登录")
    @PostMapping("/login")
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

    // TODO 使用redis缓存必要数据，分页查询key为分页页数，每天定期清理非5,10页数
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
        //在方法中设置的username 无法使用@CacheEvict
        cleanCache(cacheName + "::" + dto.getUsername().hashCode());
        return Result.success();
    }

    @ApiOperation("用户注销账号")
    @PostMapping("/logout")
    public Result logout(String password) {
        log.info("注销用户:{}", BaseContext.getCurrentId());
        userService.deleteWithUser(password);
        return Result.success();
    }

    /**
     * 清理缓存数据
     *
     * @param pattern
     */
    private void cleanCache(String pattern) {
        Set keys = redisTemplate.keys(pattern);
        redisTemplate.delete(keys);
    }
}
