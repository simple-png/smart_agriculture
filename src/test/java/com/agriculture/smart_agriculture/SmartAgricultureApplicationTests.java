package com.agriculture.smart_agriculture;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;
@SpringBootTest
class SmartAgricultureApplicationTests {
    @Autowired
    private RedisTemplate redisTemplate;


    @Test
    void contextLoads() {
        redisTemplate.expire("us374", Duration.ofDays(10));
    }

}
