package com.agriculture.aspect;

import com.agriculture.common.constant.MessageConstant;
import com.agriculture.common.exception.BaseException;
import com.agriculture.common.properties.JwtProperties;
import com.agriculture.pojo.DTO.UserDTO;
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

    /**
     * 切入点
     */
    @Pointcut("execution(* com.agriculture.controller.user.userLoginController.login(..))")

    public void autoFillPointCut() {
    }

    @AfterReturning("autoFillPointCut()")
    public void setExpire(JoinPoint joinPoint) {
        log.info("redisTemplate: {}", redisTemplate);
        log.info("开始设置key过期时间");
        Object[] args = joinPoint.getArgs();
        if (!(args[0] instanceof UserDTO))
            throw new BaseException(MessageConstant.UNKNOWN_ERROR);
        UserDTO dto = (UserDTO) args[0];
        String cacheKey = "user" + "::" + dto.getUsername().hashCode();
        redisTemplate.expire(cacheKey, jwtProperties.getUserTtl(), TimeUnit.MILLISECONDS);
    }

}
