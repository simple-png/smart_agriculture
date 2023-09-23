package com.agriculture;

import com.agriculture.common.properties.JwtProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement //开启注解方式的事务管理
@EnableCaching //开启缓存注解功能
@Slf4j
@EnableConfigurationProperties(JwtProperties.class)
@EnableScheduling //开启任务调度
public class SmartAgricultureApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartAgricultureApplication.class, args);
    }

}
