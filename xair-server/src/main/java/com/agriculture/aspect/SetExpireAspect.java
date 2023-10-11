package com.agriculture.aspect;

import com.agriculture.constant.MessageConstant;
import com.agriculture.exception.BaseException;
import com.agriculture.properties.JwtProperties;
import com.agriculture.DTO.UserDTO;
import com.agriculture.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

// TODO 写死的设置过期时间 需要优化
@Aspect
@Component
@Slf4j
@Order(2)
public class SetExpireAspect {
    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserService userService;

    /**
     * 切入点
     */
    @Pointcut("execution(* com.agriculture.controller.user.userLoginController.login(..))")

    public void autoFillPointCut() {
    }

    @AfterReturning("autoFillPointCut()")
    public void setExpire(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (!(args[0] instanceof UserDTO))
            throw new BaseException(MessageConstant.UNKNOWN_ERROR);
        UserDTO dto = (UserDTO) args[0];
        //验证用户密码是否正确
        userService.login(dto);
        String cacheKey = "user" + "::" + dto.getUsername().hashCode();
        Long expire = redisTemplate.getExpire(cacheKey);
        if (expire == -1) {
            log.info("开始设置key过期时间");
            redisTemplate.expire(cacheKey, jwtProperties.getUserTtl(), TimeUnit.MILLISECONDS);
        }
    }

}
