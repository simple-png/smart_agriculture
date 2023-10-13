package com.agriculture.aspect;

import com.agriculture.DTO.AdminDTO;
import com.agriculture.DTO.UserDTO;
import com.agriculture.constant.MessageConstant;
import com.agriculture.context.IpContext;
import com.agriculture.context.UserAgentContext;
import com.agriculture.entity.LoginLog;
import com.agriculture.exception.BaseException;
import com.agriculture.mapper.LogMapper;
import com.agriculture.service.AmapService;
import com.agriculture.utils.UserAgentUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

@Aspect
@Component
@Slf4j
public class LogAspect {
    @Autowired
    private UserAgentUtils userAgentUtils;
    @Autowired
    private LogMapper logMapper;
    @Autowired
    private AmapService amapService;

    //切入点
    @Pointcut("@annotation(com.agriculture.annotation.LoginLogAnnotation)")
    public void loginLogPointCut() {
    }

    @Around("loginLogPointCut()")
    public Object logMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable{
        try {
            Object result = joinPoint.proceed();
            loginLog(joinPoint, "登陆成功");
            return result;
        } catch (Exception ex) {
            loginLog(joinPoint, "登录失败");
            throw ex;
        }
    }

    private void loginLog(JoinPoint joinPoint, String description) {
        Object[] args = joinPoint.getArgs();
        String name;
        if (args[0] instanceof UserDTO) {
            UserDTO userDTO = (UserDTO) args[0];
            name = userDTO.getUsername();
        } else if (args[0] instanceof AdminDTO) {
            AdminDTO adminDTO = (AdminDTO) args[0];
            name = adminDTO.getUsername();
        } else throw new BaseException(MessageConstant.FAILED_TO_GET_ARGS);
        String userAgent = UserAgentContext.getUserAgent();
        Map<String, String> uaParsedMap = userAgentUtils.parseOsAndBrowser(userAgent);
        Map<String, Object> location = amapService.getLocation();
        System.out.println(location);
        String province = (String) location.get("province");
        String city = (String) location.get("city");
        String ip = IpContext.getCurrentIp();
        String ipSource = province + "|" + city;
        String os = uaParsedMap.get("os");//操作系统
        String browser = uaParsedMap.get("browser");//浏览器名称版本
        LoginLog loginLog = LoginLog.builder()
                .username(name)
                .ip(ip)
                .ipSource(ipSource)
                .os(os)
                .browser(browser)
                .createTime(LocalDateTime.now())
                .description(description)
                .userAgent(userAgent)
                .build();
        logMapper.addLoginLog(loginLog);
    }
}
