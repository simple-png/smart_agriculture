package com.agriculture.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginLog {
    private Long id;
    private String username;//用户昵称
    private String ip;//ip地址
    private String ipSource;//ip地址所在地方
    private String os;//操作系统
    private String browser;//浏览器版本号
    private String description;//是否登录成功
    private LocalDateTime createTime;//创建时间
    private String userAgent;//ua
}
