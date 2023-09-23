package com.agriculture.controller.user;

import com.agriculture.common.utils.IpUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user/ip")
@Slf4j
@Api(tags = "ip地址api")
public class IpController {
    @RequestMapping("/urlName.do")
    public String MethodName(HttpServletRequest request){
        String ip = IpUtil.getIpAddress(request);
        return ip;
    }
}
