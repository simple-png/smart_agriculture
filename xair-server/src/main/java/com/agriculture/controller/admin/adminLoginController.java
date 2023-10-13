package com.agriculture.controller.admin;

import com.agriculture.annotation.LoginLogAnnotation;
import com.agriculture.properties.JwtProperties;
import com.agriculture.entity.Admin;
import com.agriculture.service.AdminService;
import com.agriculture.constant.JwtClaimsConstant;
import com.agriculture.result.Result;
import com.agriculture.utils.JwtUtil;
import com.agriculture.DTO.AdminDTO;
import com.agriculture.VO.AdminVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/admin")
@Api(tags = "管理员登录api")
public class adminLoginController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @param adminDTO
     * @return
     */
    @LoginLogAnnotation
    @PostMapping("/login")
    @ApiOperation("管理员登录")
    public Result<AdminVO> login(@RequestBody AdminDTO adminDTO) {
        log.info("管理员：{}", adminDTO);

        Admin admin = adminService.login(adminDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.ADMIN_ID, admin.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);
        AdminVO adminVO1 = AdminVO.builder()
                .id(admin.getId())
                .username(admin.getUsername())
                .name(admin.getName())
                .token(token)
                .build();

        return Result.success(adminVO1);
    }
}
